import org.scalatest.{FunSpec, Matchers}

class MatrixParserTest extends FunSpec with Matchers{
  val EOL: String = sys.props("line.separator")
  val matrix: String = "1, 2, 3" + EOL +
    "4, 5, 6"  + EOL +
    "7, 8, 9" + EOL +
    "10, 11, 12" + EOL +
    "13, 14, 15"

  describe(s"Parses matrix string into list of lists") {
    it("should match hardcoded matrix") {
      val m = List(
        List(1,2,3),
        List(4,5,6),
        List(7,8,9),
        List(10,11,12),
        List(13, 14, 15))

      val parsedMatrix = MatrixParser.parse(matrix)

      assert(m == parsedMatrix, "matrices not equal")
    }

    it("should return an empty matrix on error") {
      val matrixBad = matrix + EOL + "124a1, abv, 19x"

      val parsedMatrix = MatrixParser.parse(matrixBad)

      assert(List() == parsedMatrix, "matrices not equal")
    }
  }
}
