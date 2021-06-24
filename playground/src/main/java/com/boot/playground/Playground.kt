package com.boot.playground

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable

@Composable
fun Playground() {
  Surface(color = MaterialTheme.colors.background) {
    //    AsyncPlayground()
    //    AnimationContainer(animRunner = { AnimAsStatePlayground(it) })
    //    AnimationContainer(animRunner = { AnimatablePlayground(it) })
//    AnimationContainer { TransitionPlayground(it) }
//    InfiniteTransitionPlayground()
//    TargetBasedAnimationPlayground()
//    DecayAnimationPlayground()
  }
}

//fun test() {
//  val list = listOf<Int>()
//  for (val index: Int, list){
//    list.subList()
//  }
//}
/*
* // You should have a top level function called main

// [10, 20, 5, 40, 80]
// k gifts

// --------X------------------------X--------
//  [10]     [20]      [5]     [40]     [80]

// 3: 125
// 5: 155

// [50, 1, 40, 40]
// k=2
// [40, 40] = 80
// k=3
// 91


fun main(args: Array<String>) {
  println("Hello, World!")
}

fun getMaxSection(gifts: Array<Int>, k: Int): Int {
  if(input.length <= k) return input.sum()
  if(k==1) return input.max()


  var maxValue: Int
  //O(N^2)
  for ((index, value) in gifts.withIndex()){
      val fromIndex = index
      val toIndex = index + k
      val newList = gifts.subList(fromIndex, toIndex)
      //index =0
      //k=2
      //[50, 1, 40, 40] =>[[50, 1], [40, 40]]

      //index =1
      //[50, 1, 40, 40] =>[[1, 40], [40] ]

      //index = 2 if(k==index)
      // [[40, 40]]

      //index = 1
      //[a, b, c, d, e, f, g, h] => b, c, d, e

      //index = 2
      //[a, b, c, d, e, f, g, h] => c, d, e, f
      val tmp = previousSum - gifts[index-1] //c, d, e

      //O(N^2)?
      for(subList in newList) { // O(N)
          var previousSum: Int? = null
          val currentSum: Int
          if previousSum == null {
              currentSum = subList.sum
          }
          else {

              currentSum = tmp+gifts[index]  // c, d, e + f
          }

          if(sum > maxValue) maxValue = sum
          previousSum = currentSum
      }
  }
  return maxValue
}


// [a, b, c, d, e, f, g, h]

// k=5


*
* */
