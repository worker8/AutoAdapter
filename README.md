# AutoAdapter

A small Android library for making RecyclerView easier to use with different view types.

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


## Quick Demo

```
fun onCreate() {
    val list = listOf(
        HeaderRow("F A K E   N E W S"),
        HorizontalListRow(horizontalList),
        NormalRow(
            NormalAutoData(
                1,
                "1. Africa declared free of wild polio",
                "728 points by riffraff 8 hours ago | hide | 125 comments"
            )
        ),
        NormalRow(
            NormalAutoData(
                2,
                "2. Gamers Will Love This: Sometimes It’s Just Fun To Relax With A Nice Jigsaw Puzzle",
                "841 points by rafaro 4 hours ago | hide | 85 comments"
            )
        ),
        NormalRow(
            NormalAutoData(
                3,
                "3. Trouble Not Your Mind, Gamers: Video Games Are Considered Cool Right Now",
                "46 points by rhasta 2 hours ago | hide | 15 comments"
            )
        ),
        NormalRow(
            NormalAutoData(
                4,
                "4. ExxonMobil To Simplify Oil Extraction By Cutting Earth In Half",
                "460 points by shaman 13 hours ago | hide | 151 comments"
            )
        ),
        FooterRow("© 2020 Fake News, Inc.")
    )
    val adapter = AutoAdapter(hasStableIds = false)
    adapter.submitList(list)
}
```
will produce this:

<img src="https://user-images.githubusercontent.com/1988156/91517829-c23e1180-e929-11ea-803b-9f8bd11ca936.png" width="300" />


## How to use

Each 'Row' has to be inherited from `BaseRow`:

```
class NormalRow(override val data: NormalAutoData) :
    BaseRow<NormalAutoData>(data, R.layout.normal_row) {
    override fun bind(itemView: View) {
        itemView.titleText.text = data.name
        itemView.descText.text = data.desc
    }
}
```

There are a few information that has to be set:

1. the layout resource id has to be set 
2. the `bind()` method
3. the `Data` class

After setting all this up, there is no need to write the adapter, instead we can use an instance of `AutoAdapter`:
```
    val adapter = AutoAdapter()
    adapter.submitList(list)
```

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
