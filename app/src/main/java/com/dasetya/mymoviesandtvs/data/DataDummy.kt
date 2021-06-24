package com.dasetya.mymoviesandtvs.data

import com.dasetya.mymoviesandtvs.entity.FavEntity
import com.dasetya.mymoviesandtvs.entity.MovieEntity
import com.dasetya.mymoviesandtvs.entity.TvEntity

object DataDummy {
    fun generateDummyMovies(): ArrayList<MovieEntity> {
        val movies = ArrayList<MovieEntity>()

        movies.add(
            MovieEntity(
                "Washed-up MMA fighter Cole Young, unaware of his heritage, and hunted by Emperor Shang Tsung's best warrior, Sub-Zero, seeks out and trains with Earth's greatest champions as he prepares to stand against the enemies of Outworld in a high stakes battle for the universe.",
                "Mortal Kombat",
                "/xGuOF1T3WmPsAcQEQJfnG7Ud9f8.jpg",
                "/9yBVqNruk6Ykrwc32qrK2TIE5xw.jpg",
                "2021-04-07",
                7.7,
                460465
            )
        )

        movies.add(
            MovieEntity(
                "In a time when monsters walk the Earth, humanity’s fight for its future sets Godzilla and Kong on a collision course that will see the two most powerful forces of nature on the planet collide in a spectacular battle for the ages.",
                "Godzilla vs. Kong",
                "/pgqgaUx1cJb5oZQQ5v0tNARCeBp.jpg",
                "/inJjDhCjfhh3RtrJWBmmDqeuSYC.jpg",
                "2021-03-24",
                8.1,
                399566
            )
        )

        movies.add(
            MovieEntity(
                "Hutch Mansell, a suburban dad, overlooked husband, nothing neighbor — a \\\"nobody.\\\" When two thieves break into his home one night, Hutch's unknown long-simmering rage is ignited and propels him on a brutal path that will uncover dark secrets he fought to leave behind.",
                "Nobody",
                "/oBgWY00bEFeZ9N25wWVyuQddbAo.jpg",
                "/6zbKgwgaaCyyBXE4Sun4oWQfQmi.jpg",
                "2021-03-26",
                8.5,
                615457
            )
        )

        movies.add(
            MovieEntity(
                "An elite Navy SEAL uncovers an international conspiracy while seeking justice for the murder of his pregnant wife.",
                "Tom Clancy's Without Remorse",
                "/rEm96ib0sPiZBADNKBHKBv5bve9.jpg",
                "/fPGeS6jgdLovQAKunNHX8l0avCy.jpg",
                "2021-04-29",
                7.3,
                567189
            )
        )


        movies.add(
            MovieEntity(
                "Victoria is a young mother trying to put her dark past as a Russian drug courier behind her, but retired cop Damon forces Victoria to do his bidding by holding her daughter hostage. Now, Victoria must use guns, guts and a motorcycle to take out a series of violent gangsters—or she may never see her child again.",
                "Vanquish",
                "/AoWY1gkcNzabh229Icboa1Ff0BM.jpg",
                "/mYM8x2Atv4MaLulaV0KVJWI1Djv.jpg",
                "2021-04-16",
                6.4,
                804435
            )
        )
        movies.add(
            MovieEntity(
                "Tanjirō Kamado, joined with Inosuke Hashibira, a boy raised by boars who wears a boar's head, and Zenitsu Agatsuma, a scared boy who reveals his true power when he sleeps, boards the Infinity Train on a new mission with the Fire Hashira, Kyōjurō Rengoku, to defeat a demon who has been tormenting the people and killing the demon slayers who oppose it!",
                "Demon Slayer -Kimetsu no Yaiba- The Movie: Mugen Train",
                "/h8Rb9gBr48ODIwYUttZNYeMWeUU.jpg",
                "/xPpXYnCWfjkt3zzE0dpCNME1pXF.jpg",
                "2020-10-16",
                8.4,
                635302
            )
        )

        movies.add(
            MovieEntity(
                "Alice, a young hearing-impaired girl who, after a supposed visitation from the Virgin Mary, is inexplicably able to hear, speak and heal the sick. As word spreads and people from near and far flock to witness her miracles, a disgraced journalist hoping to revive his career visits the small New England town to investigate. When terrifying events begin to happen all around, he starts to question if these phenomena are the works of the Virgin Mary or something much more sinister.",
                "The Unholy",
                "/b4gYVcl8pParX8AjkN90iQrWrWO.jpg",
                "/zDq2pwPyt4xwSFHKUoNN2LohDWj.jpg",
                "2021-03-31",
                5.7,
                632357
            )
        )

        movies.add(
            MovieEntity(
                "To join Adrien in Shanghai, Marinette is going to visit her uncle Wang who is celebrating his anniversary. But, as soon as she arrives in China, her purse gets stolen with Tikki inside, whom she needs to secretly transform into Ladybug! Without money and alone in the immense city, Marinette accepts the help of a young and resourceful girl, Fei. The two girls will ally and discover the existence of a new magical jewel, the Prodigious. Hawk Moth, also present in Shanghai, seeks to finding it since a long time...",
                "Miraculous World: Shanghai – The Legend of Ladydragon",
                "/msI5a9TPnepx47JUb2vl88hb80R.jpg",
                "/lHhc60NXYzswU4TvKSo45nY9Jzs.jpg",
                "2021-04-04",
                7.8,
                726684
            )
        )

        movies.add(
            MovieEntity(
                "Determined to ensure Superman's ultimate sacrifice was not in vain, Bruce Wayne aligns forces with Diana Prince with plans to recruit a team of metahumans to protect the world from an approaching threat of catastrophic proportions.",
                "Zack Snyder's Justice League",
                "/tnAuB8q5vv7Ax9UAEje5Xi4BXik.jpg",
                "/pcDc2WJAYGJTTvRSEIpRZwM3Ola.jpg",
                "2021-03-18",
                8.5,
                791373
            )
        )

        movies.add(
            MovieEntity(
                "Drac tries out some new monster pets to help occupy Tinkles for playtime.",
                "Monster Pets: A Hotel Transylvania Short",
                "/dkokENeY5Ka30BFgWAqk14mbnGs.jpg",
                "/c7dFSqZQYqNNJVpacpIGZe3gkLW.jpg",
                "2021-04-02",
                7.6,
                813258
            )
        )



        return movies
    }

    fun generateDummyTvs(): ArrayList<TvEntity> {
        val Tvs = ArrayList<TvEntity>()

        Tvs.add(
            TvEntity(
                "Mark Grayson is a normal teenager except for the fact that his father is the most powerful superhero on the planet. Shortly after his seventeenth birthday, Mark begins to develop powers of his own and enters into his father’s tutelage.",
                "Invincible",
                "/yDWJYRAwMNKbIYT8ZB33qy84uzO.jpg",
                "/6UH52Fmau8RPsMAbQbjwN3wJSCj.jpg",
                "2021-03-26",
                8.9,
                95557
            )
        )

        Tvs.add(
            TvEntity(
                "Following the events of “Avengers: Endgame”, the Falcon, Sam Wilson and the Winter Soldier, Bucky Barnes team up in a global adventure that tests their abilities, and their patience.",
                "The Falcon and the Winter Soldier",
                "/6kbAMLteGO8yyewYau6bJ683sw7.jpg",
                "/b0WmHGc8LHTdGCVzxRb3IBMur57.jpg",
                "2021-03-19",
                7.9,
                88396
            )
        )

        Tvs.add(
            TvEntity(
                "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \"meta-human\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Flash.",
                "The Flash",
                "/lJA2RCMfsWoskqlQhXPSLFQGXEJ.jpg",
                "/z59kJfcElR9eHO9rJbWp4qWMuee.jpg",
                "2014-10-07",
                7.7,
                60735
            )
        )

        Tvs.add(
            TvEntity(
                "A young surgeon with Savant syndrome is recruited into the surgical unit of a prestigious hospital. The question will arise: can a person who doesn't have the ability to relate to people actually save their lives",
                "The Good Doctor",
                "/53P8oHo9cfOsgb1cLxBi4pFY0ja.jpg",
                "/mZjZgY6ObiKtVuKVDrnS9VnuNlE.jpg",
                "2017-09-25",
                8.6,
                71712
            )
        )

        Tvs.add(
            TvEntity(
                "Follows the personal and professional lives of a group of doctors at Seattle’s Grey Sloan Memorial Hospital.",
                "Grey's Anatomy",
                "/clnyhPqj1SNgpAdeSS6a6fwE6Bo.jpg",
                "/edmk8xjGBsYVIf4QtLY9WMaMcXZ.jpg",
                "2005-03-27",
                8.2,
                1416
            )
        )

        Tvs.add(
            TvEntity(
                "The series dramatizes the life story of Mexican superstar singer Luis Miguel, who has captivated audiences in Latin America and beyond for decades.",
                "Luis Miguel: The Series",
                "/34FaY8qpjBAVysSfrJ1l7nrAQaD.jpg",
                "/wkyzeBBKLhSg1Oqhky5yoiFF2hG.jpg",
                "2018-04-22",
                8.0,
                79008
            )
        )

        Tvs.add(
            TvEntity(
                "Bored and unhappy as the Lord of Hell, Lucifer Morningstar abandoned his throne and retired to Los Angeles, where he has teamed up with LAPD detective Chloe Decker to take down criminals. But the longer he's away from the underworld, the greater the threat that the worst of humanity could escape.",
                "Lucifer",
                "/4EYPN5mVIhKLfxGruy7Dy41dTVn.jpg",
                "/ta5oblpMlEcIPIS2YGcq9XEkWK2.jpg",
                "2016-01-25",
                8.5,
                63174
            )
        )

        Tvs.add(
            TvEntity(
                "Set in the present, the series offers a bold, subversive take on Archie, Betty, Veronica and their friends, exploring the surreality of small-town life, the darkness and weirdness bubbling beneath Riverdale’s wholesome facade.",
                "Riverdale",
                "/wRbjVBdDo5qHAEOVYoMWpM58FSA.jpg",
                "/qZtAf4Z1lazGQoYVXiHOrvLr5lI.jpg",
                "2017-01-26",
                8.6,
                69050
            )
        )

        Tvs.add(
            TvEntity(
                "As Mexican-American Tejano singer Selena comes of age and realizes her dreams, she and her family make tough choices to hold on to love and music.",
                "Selena: The Series",
                "/mYsWyfiIMxx4HDm0Wck7oJ9ckez.jpg",
                "/Wu8kh7oyvaIfkNyMJyJHCamh5L.jpg",
                "2020-12-04",
                7.5,
                97180
            )
        )

        Tvs.add(
            TvEntity(
                "Seven noble families fight for control of the mythical land of Westeros. Friction between the houses leads to full-scale war. All while a very ancient evil awakens in the farthest north. Amidst the war, a neglected military order of misfits, the Night's Watch, is all that stands between the realms of men and icy horrors beyond.",
                "Game of Thrones",
                "/u3bZgnGQ9T01sWNhyveQz0wH0Hl.jpg",
                "/suopoADq0k8YZr4dQXcU6pToj6s.jpg",
                "2011-04-17",
                8.4,
                1399
            )
        )


        return Tvs
    }

    fun generateDummyFavorite(): ArrayList<FavEntity> {
        val movies = ArrayList<FavEntity>()

        movies.add(
            FavEntity(
                "MOVIE",
                "Mortal Kombat",
                "/9yBVqNruk6Ykrwc32qrK2TIE5xw.jpg",
                "2021-04-07",
                7.7,
                460465
            )
        )

        movies.add(
            FavEntity(
                "MOVIE",
                "Godzilla vs. Kong",
                "/pgqgaUx1cJb5oZQQ5v0tNARCeBp.jpg",
                "2021-03-24",
                8.1,
                399566
            )
        )

        movies.add(
            FavEntity(
                "MOVIE",
                "Nobody",
                "/oBgWY00bEFeZ9N25wWVyuQddbAo.jpg",
                "2021-03-26",
                8.5,
                615457
            )
        )

        movies.add(
            FavEntity(
                "MOVIE",
                "Tom Clancy's Without Remorse",
                "/rEm96ib0sPiZBADNKBHKBv5bve9.jpg",
                "2021-04-29",
                7.3,
                567189
            )
        )


        movies.add(
            FavEntity(
                "MOVIE",
                "Vanquish",
                "/AoWY1gkcNzabh229Icboa1Ff0BM.jpg",
                "2021-04-16",
                6.4,
                804435
            )
        )

        return movies
    }
}