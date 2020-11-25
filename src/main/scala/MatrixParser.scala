import scala.util.parsing.combinator.RegexParsers

object MatrixParser extends RegexParsers {
  override protected val whiteSpace = """ """.r
  val EOL: String = sys.props("line.separator")
  val num = """-?\d+""".r

  def cell: Parser[Int] = num ^^ {_.toInt}
  def row: Parser[List[Int]] = repsep(cell, ",")
  def matrix: Parser[List[List[Int]]] = repsep(row, EOL)

  def parse(s: String): List[List[Int]] = parseAll(matrix, s) match {
    case Success(res, _) => res
    case _ => List[List[Int]]()
  }

  def main(args: Array[String]): Unit = {
    val s = "412, 615, -415" + EOL +
      "123, -84, 1" + EOL +
      "56, 78, 15"

    val m = parse(s)
    println(m)
    println(m(0)(2) < 0)
  }
}
