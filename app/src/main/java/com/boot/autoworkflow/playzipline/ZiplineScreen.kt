package com.boot.autoworkflow.playzipline

import androidx.compose.runtime.Composable
import app.cash.zipline.ZiplineService

@Composable fun ZiplineScreen() {}

interface TriviaService : ZiplineService {
  fun games(): List<TriviaGame>
  fun answer(questionId: String, answer: String): AnswerResult
}

class TriviaGame

class AnswerResult

class RealTriviaService : TriviaService {
  override fun games(): List<TriviaGame> {
    return emptyList()
  }

  override fun answer(questionId: String, answer: String): AnswerResult {
    return AnswerResult()
  }
}
