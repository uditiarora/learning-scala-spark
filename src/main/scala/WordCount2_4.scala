import org.apache.spark.{SparkConf, SparkContext}
import util.FileUtil

object WordCount2_4 {
  def main(args: Array[String]): Unit = {

    val sc = new SparkContext("local", "Word Count (2)", new SparkConf())

    try {
      val out = "output/kjv-wc2"
      FileUtil.rmrf(out)

      val input = sc.textFile("data/kjvdat.txt").map(line => line.toLowerCase)
      val wc = input
        .flatMap(line => line.split("""[^\p{IsAlpha}]+"""))
        .map(word => (word, 1))
        .reduceByKey((count1, count2) => count1 + count2)
        .groupBy(tuple => tuple._2)
        .sortByKey(ascending = true)

      println(s"Writing output to: $out")
      wc.saveAsTextFile(out)
    } finally {
      sc.stop()
    }
  }
}
