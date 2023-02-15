package com.boot.ziplineplayground

import app.cash.zipline.ZiplineService
import kotlinx.serialization.Serializable

interface TriviaService : ZiplineService {
	fun games(): List<TriviaGame>
	fun answer(gameId: Int, questionId: Int, answer: String): AnswerResult
}

@Serializable
class TriviaGame(
	val id: Int,
	val name: String,
	val questions: List<Question>,
)

@Serializable
class Question(
	val id: Int,
	val text: String,
)

@Serializable
class AnswerResult(
	val correct: Boolean,
	val message: String,
)
