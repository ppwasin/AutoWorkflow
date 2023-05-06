package com.boot.playground.animation.transition

data class Hotel(
	val id: Int,
	val name: String,
	val img: String,
	val ratingStar: Int,
	val location: String,
	val ratingDescription: String,
	val reviewsCount: Int
)

data class Notification(
	val id: Int,
	val img: String,
	val sender: String,
	val sendDate: String,
	val type: Type,
	val priority: Priority,
	val title: String,
	val description: String
) {
	enum class Type {
		General, Event,
	}

	enum class Priority {
		Informational, Urgent
	}

	companion object {
		fun fakeList(): List<Notification> {
			return (1..20).map { fake(it) }
		}

		private fun fake(id: Int): Notification {
			return Notification(
				id = id,
				img = "https://picsum.photos/id/$id/200/300",
				sender = "Mheevun",
				sendDate = "May 2, 2023",
				type = Type.General,
				priority = Priority.Informational,
				title = "Lorem Title",
				description = longString(),
			)
		}
	}
}


private fun longString() =
	"Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum."
