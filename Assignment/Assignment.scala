val lines = sc.textFile("File:///home/cloudera/Desktop/data files/data files/twitter/1millionTweets.tsv")
val split_lines = lines.map(_.split("\t"))


//Question 2)a.
split_lines.map(x => ((x(1), x(3)), x(2).toInt)).reduceByKey((x, y) => Math.max(x, y)).sortBy(x=> x._2, false).take(1)

//Question 2)b.
split_lines.map(x => (x(3), x(2).toInt)).reduceByKey(_ + _).sortBy(x=> x._2, false).take(1)


//Question 3)a.
val bag = sc.textFile("File:///home/cloudera/Desktop/data files/data files/Bag of words/docword.txt")
val split_lines = bag.map(_.split(" "))
val vocab = sc.textFile("File:///home/cloudera/Desktop/data files/data files/Bag of words/vocab.txt")

val temp = vocab.zipWithIndex.map(_ match {case (k,v) => ((v + 1).toInt,k)})
val combined = split_lines.map(x => (x(1).toInt, x(2).toInt)).reduceByKey(_ + _).join(temp).sortBy(x=> x._1, true).map(x => (x._2._2, x._2._1))
combined.saveAsTextFile("File:///home/cloudera/Desktop/task")


//Question 3)b.
val bag = sc.textFile("File:///home/cloudera/Desktop/data files/data files/Bag of words/docword.txt")
val split_lines = bag.map(_.split(" "))
val vocab = sc.textFile("File:///home/cloudera/Desktop/data files/data files/Bag of words/vocab.txt")
val temp = vocab.zipWithIndex.map(_ match {case (k,v) => ((v + 1).toInt,k)})

val words = split_lines.map(x => (x(1).toInt, (x(0), x(2)))).groupByKey().join(temp).map(x => (x._2._2,x._2._1))
words.saveAsObjectFile("File:///home/cloudera/Desktop/InvertedIndex")



//Question 3)c.
val loadedWords = sc.objectFile[String, (String,String)]("File:///home/cloudera/Desktop/InvertedIndex")
val inputWord = "back"
loadedWords.filter(x => x._1 == imputWord)