class Game {

    private val candidates: HashMap<Int, HashMap<Int, Int>> = HashMap()
    private var previousGeneration: HashMap<Int, MutableList<Int>> = HashMap()
    private var presentGeneration: HashMap<Int, MutableList<Int>> = HashMap()

    data class Cell(val row: Int, val col: Int)

    private fun findCandidates() {
        candidates.clear()
        presentGeneration.map { row ->
            row.value.map { col ->
                registerNeighbours(Cell(row.key, col))
            }
        }
    }

    private fun swapGenerations() {
        previousGeneration = presentGeneration
        presentGeneration = HashMap()
    }

    private fun makeNewGeneration() {
        candidates.map { row ->
            row.value.map { col ->
                val cell = Cell(row.key, col.key)
                if (gonnaLive(cell, col.value)) produce(cell)
            }
        }
    }

    private fun gonnaLive(cell: Cell, neighboursCount: Int): Boolean = when (neighboursCount) {
        3 -> true
        2 -> isAlive(cell)
        else -> false
    }

    private fun produce(cell: Cell) {
        if (!presentGeneration.containsKey(cell.row)) presentGeneration[cell.row] = mutableListOf(cell.col)
        else presentGeneration[cell.row]?.add(cell.col)
    }

    private fun isAlive(cell: Cell): Boolean = previousGeneration[cell.row]?.contains(cell.col) == true

    private fun registerNeighbours(cell: Cell) {
        for (deltaRow in -1..1) {                      // borderline cases!!!
            val row = cell.row + deltaRow

            if (candidates[row] == null) candidates[row] = HashMap()

            for (deltaCol in -1..1) {                      // borderline cases!!!
                val col = cell.col + deltaCol

                candidates[row]!![col] = if (candidates[row]!![col] == null) 1 else candidates[row]!![col]!! + 1
            }
        }
    }
}
