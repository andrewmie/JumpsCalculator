import java.util.*

class Solution {
    data class State(val index: Int, val jumps: Int)

    fun calculateJumps(numbersArray: IntArray): Int {
        val nextPos = numbersArray.indices
        val arraySize = numbersArray.size
        val q: Queue<State> = LinkedList()
        q.offer(State(0, 0))
        val jumped: MutableSet<Int> = HashSet()
        jumped.add(0)

        while (q.isNotEmpty()) {
            val (currIndex, jumps) = q.poll()

            if (currIndex == arraySize - 1) {
                return jumps
            }

            val nextIndexPositive = currIndex + numbersArray[currIndex]
            val nextIndexNegative = currIndex - numbersArray[currIndex]

            if (nextIndexPositive in nextPos && !jumped.contains(nextIndexPositive)) {
                q.offer(State(nextIndexPositive, jumps + 1))
                jumped.add(nextIndexPositive)
            }
            if (nextIndexNegative in nextPos && !jumped.contains(nextIndexNegative)) {
                q.offer(State(nextIndexNegative, jumps + 1))
                jumped.add(nextIndexNegative)
            }
        }
        return -1
    }
}

fun main() {
    val inputArray = intArrayOf(4, 1, 3, 2, 2, 2, 5, 1, 2, 3, 6, 1, 1)
    val solution = Solution()
    val minJumps = solution.calculateJumps(inputArray)

    if (minJumps != -1) {
        println("Minimum jumps to reach the end: $minJumps")
    } else {
        println("Cannot reach the end.")
    }
}