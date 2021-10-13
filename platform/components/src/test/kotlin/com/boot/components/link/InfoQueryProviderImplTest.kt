package com.boot.components.link

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class InfoQueryProviderImplTest {
    private lateinit var fakeMemberService: FakeMemberService
    private lateinit var target: InfoQueryProviderImpl
    @BeforeEach
    fun setup(){
        fakeMemberService = FakeMemberService()
        target = InfoQueryProviderImpl(fakeMemberService)
    }

    @Test
    fun `should return member id if user login`(){
        fakeMemberService.login(1234)
        val paramMap = target.getLinkQueryParams()

        assert(paramMap["memberId"] == "1234")
    }

    @Test
    fun `should not return member id if user not login`(){
        val paramMap = target.getLinkQueryParams()

        assert(paramMap["memberId"] == null)
    }

    @Test
    fun `should return selected price type`(){
        val paramMap = target.getLinkQueryParams()

        assert(paramMap["mpriceView"] == )
    }
}


//fun runLengthEncoding(string: String): String {
//    val encodingList = mutableListOf<String>()
//    var runningIndex = 0
//    while (runningIndex < string.lastIndex){
//        val char = string[runningIndex]
//        val duplicateCount = getDuplicateCount(string, startIndex = runningIndex)
//        runningIndex += duplicateCount
//
//        val encoding: String = when {
//            duplicateCount <= 1 -> ""
//            duplicateCount <= 9 -> "$duplicateCount$char"
//            else -> {
//                val full = duplicateCount / 9
//                val mod = duplicateCount % 9
//                val ninePrefix = "9$char".repeat(full)
//                "$ninePrefix$mod$char"
//            }
//        }
//
//        encodingList.add(encoding)
//    }
//
//    return encodingList.joinToString("")
//}
//
//fun getDuplicateCount(
//    string: String,
//    startIndex: Int
//): Int {
//    var count = 0
//    println("start: ${string[startIndex]}")
//    for(runningIndex in startIndex until string.length){
//        if(string[startIndex] == string[runningIndex])
//            count += 1
//        else
//            break
//    }
//    println(count)
//    return count
//}


fun firstNonRepeatingCharacter(string: String): Int {
    val countMap = mutableMapOf<Char, Int>()

    string.forEach { char ->
        countMap[char] = (countMap[char] ?: 0)+1
    }

    return string.indexOfFirst { countMap[it] == 1 }
}