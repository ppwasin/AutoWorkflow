package com.boot.playground.ds.unorganize

// fun dfsWithoutRecursion(adjVertices: Array<Int>, start: Int) {
//    val stack: Stack<Int> = Stack<Int>()
//    val isVisited = BooleanArray(adjVertices.size)
//    stack.push(start)
//    while (!stack.isEmpty()) {
//        val current: Int = stack.pop()
//        isVisited[current] = true
//        visit(current)
//        for (dest in adjVertices[current]) {
//            if (!isVisited[dest]) stack.push(dest)
//        }
//    }
// }

data class State(val nextWaiting: Int, val sum: Int)

fun minimumWaitingTime(queries: MutableList<Int>): Int {
	val newList = queries.sorted()
	/*
	[1,    2,          2,       3,      6 ]
	1,0    1+2,0+1     1+2+2
	*/
	return newList
		.scan(State(0, 0)) { acc: State, item: Int ->
			val currentWaitingTime = acc.nextWaiting
			acc.copy(
				nextWaiting = currentWaitingTime + item,
				sum = acc.sum + currentWaitingTime,
			)
		}
		.last()
		.sum
}

fun main() {
	val list = mutableListOf(2, 4, 5)
	list.sortDescending()
	println(list)
}

fun tandemBicycle(
	redShirtSpeeds: MutableList<Int>,
	blueShirtSpeeds: MutableList<Int>,
	fastest: Boolean
): Int {
	redShirtSpeeds.sortDescending()
	if (fastest) blueShirtSpeeds.sort() else blueShirtSpeeds.sortDescending()

	var sum = 0
	for (index in 0 until redShirtSpeeds.size) {
		sum +=
			if (redShirtSpeeds[index] > blueShirtSpeeds[index]) redShirtSpeeds[index]
			else blueShirtSpeeds[index]
	}

	return sum
}
