val lines = sc.textFile("File:///home/cloudera/Desktop/data files/data files/twitter/1millionTweets.tsv")
val split_lines = lines.map(_.split("\t"))


//Question 2)a.
split_lines.map(x => ((x(1), x(3)), x(2).toInt)).reduceByKey((x, y) => Math.max(x, y)).sortBy(x=> x._2, false).take(1)

//Question 2)b.
split_lines.map(x => (x(3), x(2).toInt)).reduceByKey(_ + _).sortBy(x=> x._2, false).take(1)