package com.boot.playground.algorithm.a1_slidingwindow

fun findAnagrams(s: String, p: String): List<Int> {
  val pmap = mutableMapOf<Char, Int>()
  var start = 0
  var end = 0
  p.forEach { pmap[it] = pmap.getOrDefault(it, 0) + 1 }
  val result = mutableListOf<Int>()
  while (end < s.length) {
    val window = s.substring(start, end + 1)

    val current = s[end]
    val pFrequency = pmap[current]

    if (pFrequency == null) {
      end++
      start++
    } else {
      pmap[current] = (pFrequency - 1)
      end++
    }

    val sMatchLength = end - start + 1
    if (sMatchLength == p.length) {
      result.add(start)

      val sStart = s[start]
      if (pmap.containsKey(sStart)) {
        pmap[sStart] = pmap[sStart]!! + 1
      }
      start++
    }

    //    if (pFrequency == null || pFrequency <= 0) {
    //      val sStart = s[start]
    //      if (pmap.containsKey(sStart)) {
    //        pmap[sStart] = pmap[sStart]!! + 1
    //      }
    //      start++
    //    }
    //
    //    if(pFrequency != null){
    //      pmap[current] = (pFrequency - 1)
    //    }
    //
    //    val sMatchLength = end - start + 1
    //    if (sMatchLength == p.length) {
    //      val target = s.substring(start, end + 1)
    //      result.add(start)
    //
    //      val sStart = s[start]
    //      if (pmap.containsKey(sStart)) {
    //        pmap[sStart] = pmap[sStart]!! + 1
    //      }
    //      start++
    //    }
    //
    //    end++
  }

  return result
}

fun main() {
  println(findAnagrams("cbaebabacd", "abc"))
}
