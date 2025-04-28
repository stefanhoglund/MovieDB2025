package com.example.moviedb2025.database

import com.example.moviedb2025.models.Movie

class Movies {
    fun getMovies(): List<Movie>{
        return listOf(
            Movie(
                1,
                "A Minecraft Movie",
                "/yFHHfHcUgGAxziP1C3lLt0q2T4s.jpg",
                "/2Nti3gYAX513wvhp8IiLL6ZDyOm.jpg",
                "2025-03-31",
                "Four misfits are suddenly pulled through a mysterious portal into a bizarre cubic wonderland that thrives on imagination. To get back home they'll have to master this world while embarking on a quest with an unexpected expert crafter.",
                listOf(14,12,28),
                "tt3566834",
                "https://www.minecraft-movie.com"
            ),
            Movie(
                2,
                "G20",
                "/tSee9gbGLfqwvjoWoCQgRZ4Sfky.jpg",
                "/k32XKMjmXMGeydykD32jfER3BVI.jpg",
                "2025-04-09",
                "After the G20 Summit is overtaken by terrorists, President Danielle Sutton must bring all her statecraft and military experience to defend her family and her fellow leaders.",
                listOf(28,9648,18),
                "tt23476986",
                "https://www.amazon.com/dp/B0DV3V4HH6",
            ),
            Movie(
                3,
                "Gunslingers",
                "/O7REXWPANWXvX2jhQydHjAq2DV.jpg",
                "/zksO4lVnRKRoaSYzh2EDn2Z3Pel.jpg",
                "2025-04-11",
                "When the most wanted man in America surfaces in a small Kentucky town, his violent history -- and a blood-thirsty mob seeking vengeance and a king’s ransom -- soon follow. As brothers face off against one another and bullets tear the town to shreds, this lightning-fast gunslinger makes his enemies pay the ultimate price for their greed.",
                listOf(37,28),
                "tt24850708",
                "None",
            ),
            Movie(
                4,
                "Cleaner",
                "/mwzDApMZAGeYCEVjhegKvCzDX0W.jpg",
                "/gsQJOfeW45KLiQeEIsom94QPQwb.jpg",
                "2025-02-19",
                "When a group of radical activists take over an energy company's annual gala, seizing 300 hostages, an ex-soldier turned window cleaner suspended 50 storeys up on the outside of the building must save those trapped inside, including her younger brother.",
                listOf(28,53),
                "tt27812086",
                "None",
            ),
            Movie(
                696506,
                "Mickey 17",
                "/edKpE9B5qN3e559OuMCLZdW1iBZ.jpg",
                "/hNA73rnG4PjSwgojaC2gbO1f8Rt.jpg",
                "2025-02-28",
                "Unlikely hero Mickey Barnes finds himself in the extraordinary circumstance of working for an employer who demands the ultimate commitment to the job… to die, for a living.",
                listOf(878,35,12),
                "tt12299608",
                "https://www.mickey17movie.com",
            )
        )
    }
}