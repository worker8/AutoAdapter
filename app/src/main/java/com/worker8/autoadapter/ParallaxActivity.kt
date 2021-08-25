package com.worker8.autoadapter

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.NO_ID
import com.worker8.auto.adapter.library.AutoAdapter
import com.worker8.auto.adapter.library.AutoData
import com.worker8.auto.adapter.library.ListItem
import com.worker8.autoadapter.data.StringData
import com.worker8.autoadapter.rows.FooterRow
import com.worker8.autoadapter.rows.ImageRow
import com.worker8.autoadapter.util.AutoIncrementingId

class ParallaxActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_parallax)
        val adapter = AutoAdapter(hasStableIds = false)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.adapter = adapter
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val view = recyclerView.getChildAt(0);
                if (view != null && recyclerView.getChildAdapterPosition(view) == 0) {
                    view.translationY = (-view.top / 2).toFloat()
                }
            }
        })
        val autoIncrementingId = AutoIncrementingId()
        val list = listOf(
            ImageRow(),
            ParallaxHeaderRow(
                ParallaxHeaderRow.HeaderAutoData(
                    tag = "SPORTS",
                    title = "Gallagher secures Britain's first ever Winter Pralympic gold medal"
                )
            ),
            ParallaxContentRow(
                StringData(
                    text = "Anything Britain's Winter Olympians can do, the Paralympians have shown they can do even better: partially sighted Kelly Gallagher has won the country's first gold medal on snow at either event.\n" +
                            "\n" +
                            "Gallagher, with her guide Charlotte Evans, took gold in the visually impaired super-G, or super giant slalom, on the slopes of Rosa Khutor high above the Russian city of Sochi.\n" +
                            "\n" +
                            "Maintaining the momentum created by fellow partially sighted skier Jade Etherington, who won silver with her guide Caroline Powell in the women's downhill on Saturday and added a bronze in the super-G on Monday , Gallagher has made her own piece of history.\n" +
                            "\n" +
                            "It is the first time since the Winter Olympics and the Winter Paralympics began – in 1924 and 1976 respectively – that a British skier has won a gold medal on the slopes." +

                            "Gallagher said that she hoped her success would inspire other disabled athletes. \"It's been a difficult journey here and we are so happy to have had so much support from the very start. Today has proven to me that hard work and self belief really does pay off,\" said the 28-year-old from Bangor in Northern Ireland.\n" +
                            "\n" +
                            "\"I'm so delighted to be part of such a unique and historical moment for British Paralympic sport and I can't wait to see more disabled skiers out on the mountain now.\"\n" +
                            "\n" +
                            "In 2010 in Vancouver, Gallagher managed fourth- and sixth-placed finishes at a Games in which the British team failed to win a medal. After just three days of competition, the 2014 team of seven skiers, plus three guides, and five wheelchair curlers now have three – one of each colour.\n" +
                            "\n" +
                            "Evans must stay within one gate of Gallagher and calls instructions through a headset to relay information about turns and snow conditions. She was just 19 when they teamed up in 2011 and they quickly formed a good rapport, winning silver and bronze at the world championships in Sestriere in Italy.\n" +
                            "\n" +
                            "Having gone one better in front of healthy crowds in Sochi, where a late surge of interest allayed fears that stands would be deserted, Evans said it would take a while to sink in.\n" +
                            "\n" +
                            "\"It wasn't great skiing today but Kelly did her best and luckily for us that was good enough,\" she said. \"I hope we changed people's perspective of disability skiing and it feels so good to know that there might be people watching who will consider taking up disability skiing as a result.\"\n" +
                            "\n" +
                            "Having completed the course first, the pair faced a nervous wait at the foot of the run as their rivals, including the Slovakian reigning champion Henrieta Farkasova, failed to better their time.ParalympicsGB's chef de mission, Penny Briscoe, said she could not be prouder of the team's achievements to date. The medals secured on Mondaymean they have already beaten the target of at least two medals in total, set by funding agency UK Sport. \"This is a historic day – not just ParalympicsGB's first gold in the Winter Games but our first gold on snow in either the Olympic or Paralympic Games,\" said Briscoe. \"Kelly and Charlotte proved what world-class athletes they are to come back after the disappointment of the downhill and are absolutely buzzing now. I'm sure the people in her home town of Bangor in Northern Ireland will be going crazy.\"\n" +
                            "\n" +
                            "British Paralympic Association chief executive Tim Hollingsworth hopes that Sochi will prove a \"step change\" for winter Paralympic sport in the same was as the London Games boosted summer disciplines.\n" +
                            "\n" +
                            "After winning her second medal of the Games, Etherington said: \"After the run that we had I'm really happy. I had a little wobble off the lake jump and hit a gate, so I'm happy to have a medal after that mistake.\"\n" +
                            "\n" +
                            "Both Gallagher and Etherington will be back in action on Tuesday in the super-combined, amid hopes they could add to the medal total. The Games began on Friday amid fears that the situation in Ukraine would overshadow events on the snow and ice. But the International Paralympic Committee and organisers have insisted it will not affect the Games.\n" +
                            "\n" +
                            "UK Sport has invested a record £750,000 of lottery and exchequer funding in winter Paralympic sport over the past four years. Its chief executive, Liz Nicholl, said that, combined with the £13m-plus poured into the Winter Olympics team, it had \"helped both teams achieve historic results and make the nation hugely proud\".\n" +
                            "\n" +
                            "British Paralympic Association chief executive Tim Hollingsworth hopes that Sochi will prove a \"step change\" for winter Paralympic sport in the same was as the London Games boosted summer disciplines.\n" +
                            "\n" +
                            "After winning her second medal of the Games, Etherington said: \"After the run that we had I'm really happy. I had a little wobble off the lake jump and hit a gate, so I'm happy to have a medal after that mistake.\"\n" +
                            "\n" +
                            "Gallagher and Etherington will be back in action on Tuesday in the super-combined, amid hopes they could add to the medal total."
                )
            ),
            FooterRow(
                FooterRow.Data(autoIncrementingId.next(), "© 2020 Fake News, Inc.")
            )
        )
        adapter.submitList(list)

    }
}

private class ParallaxHeaderRow(override val data: HeaderAutoData) :
    ListItem<ParallaxHeaderRow.HeaderAutoData>() {
    override val layoutResId = R.layout.parallax_header_row
    override fun bind(itemView: View, position: Int) {
        itemView.apply {
            val parallaxTagText = findViewById<TextView>(R.id.parallaxTagText)
            val parallaxTitleText = findViewById<TextView>(R.id.parallaxTitleText)
            parallaxTagText.text = data.tag
            parallaxTitleText.text = data.title
        }
    }

    data class HeaderAutoData(override val id: Long = NO_ID, val tag: String, val title: String) :
        AutoData {
        override fun isContentSame(other: AutoData): Boolean {
            return this == other
        }
    }
}

private class ParallaxContentRow(override val data: StringData) :
    ListItem<StringData>() {
    override val layoutResId = R.layout.parallax_content_row
    override fun bind(itemView: View, position: Int) {
        itemView.apply {
            val parallaxContentText = findViewById<TextView>(R.id.parallaxContentText)
            parallaxContentText.text = data.text
        }
    }
}
