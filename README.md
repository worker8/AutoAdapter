# AutoAdapter

[A small Android library](https://github.com/worker8/AutoAdapter/tree/master/library/src/main/java/com/worker8/auto/adapter/library) for making `RecyclerView` easier to use with different view types. 

## Install

build.gradle:

```
    allprojects {
	repositories {
            maven { url 'https://jitpack.io' }
        }
    }
    
    // if using .kts
    allprojects {
        repositories {
            maven("https://jitpack.io")
    }
}
```

app/build.gradle:

```
dependencies {
    implementation 'com.github.worker8:AutoAdapter:-SNAPSHOT'
    // if using .kts
    implementation("com.github.worker8:AutoAdapter:-SNAPSHOT")
}
```
Replace `-SNAPSHOT` with the latest version found here to use the latest version: https://github.com/worker8/AutoAdapter/releases


## Usage by examples

| Example | After |
| - | - |
| [Same View Type Example](https://github.com/worker8/AutoAdapter/blob/master/app/src/main/java/com/worker8/autoadapter/SimpleActivity.kt)  | <img src="https://user-images.githubusercontent.com/1988156/92556121-1808a700-f2a4-11ea-8844-e00cdfbc73f7.png" width="200px" />  |
| [Multiple View Type Example](https://github.com/worker8/AutoAdapter/blob/master/app/src/main/java/com/worker8/autoadapter/MultipleViewTypeActivity.kt) | <img src="https://user-images.githubusercontent.com/1988156/92556136-1f2fb500-f2a4-11ea-957c-236df89631cb.png" width="200px" />  |
| [Parallax Header Detail Page Example](https://github.com/worker8/AutoAdapter/blob/master/app/src/main/java/com/worker8/autoadapter/ParallaxActivity.kt) | <img src="https://user-images.githubusercontent.com/1988156/92556132-1dfe8800-f2a4-11ea-9681-5c46d0474b4f.png" width="200px" /> |

## How to use
Let's begin with something simple, let's say we want to build a row like this:
 
<img src="https://user-images.githubusercontent.com/1988156/92567086-252f9100-f2b8-11ea-939b-ca476660c217.png" width="300px" />

We need 3 steps for this:

### 1. `ListItem`
First, we need to make  `ListItem`.
 
`ListItem` is an interface describing 3 things: 

```
abstract class ListItem<out T : AutoData> {
    abstract val data: T

    @get:LayoutRes
    abstract val layoutResId: Int
    abstract fun bind(itemView: View)
}
```

- the `data`
- the `layout resource id`
- the `bind()` method

Here's how to inherit from `ListItem`:

```
class NormalRow(override val data: NormalAutoData) : ListItem<NormalAutoData>() { // Explanation i.

    override val layoutResId = R.layout.normal_row // Explanation ii.

    override fun bind(itemView: View, position: Int) { // Explanation iii.
        itemView.titleText.text = data.name
        itemView.descText.text = data.desc
    }
}
```

**Explanation:**

i. `NormalAutoData` is a class inherited from `AutoData` (will be described more in the next section). This holds the data itself, in this case, it is the `title #n` and the `desc #n` strings.

ii. we pass in the xml resource id to be used for this row.

iii. we override the `bind()` method, this method will be called by `RecyclerView` when this row appears in the screen.

### 2. `AutoData`

`AutoData` is an interface for describing an object that has an `id` where it's content can be comparable: 

```
interface AutoData {
    val id: Long
    fun isContentSame(other: AutoData): Boolean
}
```
This 2 information will be used by the `DiffUtil` of `RecyclerView` to calculate the the changes that have happened to properly animating and reusing the views.

In this case, our `NormalAutoData` will hold 2 things, the `title #n` and the `desc #n` strings:

```
data class NormalAutoData(override val id: Long, val name: String, val desc: String) : AutoData { // i, ii
    override fun isContentSame(other: AutoData): Boolean { // iii
        return this == other
    }
}
```

**Explanation:**

i. the `id` will be used by [RecyclerView.Adapter#getItemId()](https://developer.android.com/reference/androidx/recyclerview/widget/RecyclerView.Adapter#getItemId(int)) to identify an item.

ii. if there is no `stable id`, [`NO_ID`](https://developer.android.com/reference/androidx/recyclerview/widget/RecyclerView#NO_ID) can be used

iii. we override the `isContentSame()` method for comparing the content, this will be used by the [`DiffUtil.ItemCallback`](https://developer.android.com/reference/androidx/recyclerview/widget/DiffUtil.ItemCallback) to calculate the difference.
- _refer to [this doc](https://developer.android.com/reference/androidx/recyclerview/widget/ListAdapter) if you are not familiar with `RecyclerView.ListAdapter`._

### 3. `AutoAdapter`
After making the `ListItem` and `AutoData`, we can finally make the list!

`AutoAdapter` inherits from [ListAdapter](https://developer.android.com/reference/androidx/recyclerview/widget/ListAdapter). When using `AutoAdapter`, it will automatically calculate the view types and position offset, so there is no need to write your own Adapter at all even for use cases with multiple different view types.

To setup `AutoAdapter`, simply make an instance in the activity:

```kotlin
fun onCreate() {
    val adapter = AutoAdapter()
    recyclerView.adapter = adapter
}
```

Since `AutoAdapter` inherits from `RecyclerView.ListAdapter`, we use the same API `adapter.submitList(list)` to populate the list:

```diff
fun onCreate() {
    val adapter = AutoAdapter()
    recyclerView.adapter = adapter
+   val list = mutableListOf<ListItem<AutoData>>()
+   list.add(NormalRow(NormalAutoData(NO_ID, "title #0", "desc #0")))
+   list.add(NormalRow(NormalAutoData(NO_ID, "title #1", "desc #1")))
+   list.add(NormalRow(NormalAutoData(NO_ID, "title #2", "desc #2")))
+   adapter.submitList(list)
}
```

Tada :tada: , now you will get this:

<img src="https://user-images.githubusercontent.com/1988156/92567086-252f9100-f2b8-11ea-939b-ca476660c217.png" width="300px" />

To see this in action, check out the example: [SimpleActivity.kt](https://github.com/worker8/AutoAdapter/blob/master/app/src/main/java/com/worker8/autoadapter/SimpleActivity.kt)

### Multiple View Types
This section describes how to use `AutoAdapter` with multiple view types. 

Let's say we want to build a `Header` like below:

<img src="https://user-images.githubusercontent.com/1988156/92580845-da1e7980-f2c9-11ea-8b61-138f555b4d9f.png" width="300px" />

The changes we need to make from the previous basic example above :point_up: is actually quite minimal.
We need to follow the same steps as above, first we need to create `HeaderRow`:

```
class HeaderRow(private val title: String) :
    ListItem<NoAutoData>() {

    override val layoutResId = R.layout.header_row

    override val data = NoAutoData()

    override fun bind(itemView: View, position: Int) {
        itemView.headerText.text = title
    }
}
```

In this case I'm using `NoAutoData`, because the `HeaderRow` is static and won't need to change once it's initialized:

```
class NoAutoData(override val id: Long = -1) : AutoData {
    override fun isContentSame(other: AutoData): Boolean {
        return true
    }
}
```

After making the `HeaderRow`, we can simply add it to the `list`:

```diff
fun onCreate() {
    val adapter = AutoAdapter()
    recyclerView.adapter = adapter
    val list = mutableListOf<ListItem<AutoData>>()
+   list.add(HeaderRow("F A K E   N E W S")
    list.add(NormalRow(NormalAutoData(NO_ID, "title #0", "desc #0")))
    list.add(NormalRow(NormalAutoData(NO_ID, "title #1", "desc #1")))
    list.add(NormalRow(NormalAutoData(NO_ID, "title #2", "desc #2")))
    adapter.submitList(list)
}
```

For a more thorough example, refer to [Multiple View Type Example](https://github.com/worker8/AutoAdapter/blob/master/app/src/main/java/com/worker8/autoadapter/MultipleViewTypeActivity.kt).

### HasStableId

`AutoAdapter` supports [RecyclerView.Adapter#setHasStableIds(boolean)
](https://developer.android.com/reference/androidx/recyclerview/widget/RecyclerView.Adapter#setHasStableIds(boolean))

When making an instance of `AutoAdapter`, you can pass in a boolean to indicate this flag:

```
val adapter = AutoAdapter(hasStableIds = true) // default is false
```

Then, pass in a unique id to the data:

```
list.add(NormalRow(
    NormalAutoData(id = uniqueId, "title", "desc")
))
```

After enablding `hasStablIds = true`, `RecyclerView` can optimize the animation when changes happen and can prevent blinking issue.

## License

```
The MIT License (MIT)

Copyright (c) 2020 Tan Jun Rong

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
