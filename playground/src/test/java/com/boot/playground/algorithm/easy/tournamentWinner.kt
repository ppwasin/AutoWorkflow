package com.boot.playground.algorithm.easy

open class BinaryTree(value: Int) {
  var value = value
  var left: BinaryTree? = null
  var right: BinaryTree? = null
}

fun branchSums(root: BinaryTree): List<Int> {
  val result = mutableListOf<Int>()
  sideSum(root, 0, result)
  return result
}

fun sideSum(current: BinaryTree?, currentSum: Int, sumList: MutableList<Int>) {
  if (current == null) return

  val newSum = currentSum + current.value
  if (current.left == null && current.right == null) sumList.add(newSum)
  else {
    sideSum(current.left, newSum, sumList)
    sideSum(current.right, newSum, sumList)
  }
}
