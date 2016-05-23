/////////////////////////////////////////////////////////////////////////////////////////////
//THIS CODE READS IN BANK DATA FROM 2 SOURCES IN THE FORMAT 
//doc1 "((DOCID) (VOCAB INDEX) (COUNT))"
//doc2 "(WORD(These words are separated by line))"
//
//Output "Task3a.txt" which is a list of the total count across all documents (vocab index starts at 1)
////////////////////////////////////////////////////////////////////////////////////////////


//Reads in file
val lines = sc.textFile("File:///home/cloudera/Desktop/data files/data files/Bag of words/docword.txt")
val vocab = sc.textFile("File:///home/cloudera/Desktop/data files/data files/Bag of words/vocab.txt")

//splits lines via tab space delimiters
val split_lines = lines.map(_.split(" "))

//create temp var which adds an indes to all vocab words and adds 1(to start indexing at 1)
val temp = vocab.zipWithIndex.map(_ match {case (k,v) => ((v + 1).toInt,k)})

//first take docID and words
val combined = split_lines.map(x => (x(1).toInt, x(2).toInt)).reduceByKey(_ + _).join(temp).sortBy(x=> x._1, true).map(x => (x._2._2, x._2._1))
combined.saveAsTextFile("File:///home/cloudera/Desktop/task")