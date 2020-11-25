import scala.util.parsing.combinator.RegexParsers

object MatrixParser extends RegexParsers {
  override protected val whiteSpace = """ """.r
  val EOL: String = sys.props("line.separator")
  val num = """\d+""".r

  def matrix: Parser[List[List[Int]]] = repsep(row, EOL) <~ opt(EOL)
  def row: Parser[List[Int]] = rep1sep(cell, ",")
  def cell: Parser[Int] = num ^^ { _.toInt}

  def parse(s: String): List[List[Int]] = parseAll(matrix, s) match {
    case Success(res, _) => res
    case _ => List[List[Int]]()
  }

  def main(args: Array[String]): Unit = {
    val s = "412, 615, 415" + EOL +
      "123, 84, 1" + EOL +
      "56, 78, 15"

    println(parse(s))
  }
}
