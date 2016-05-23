/////////////////////////////////////////////////////////////////////////////////////////////
//THIS CODE READS IN TWITTER DATA IN THE FORMAT 
//"(TYPE (eg Hashtag)) (MONTH (eg 200908)) (COUNT (integer)) (Hashtag name(eg #LOL $YOLO))"
//
//Find the hashtag name that has been tweeted the most
////////////////////////////////////////////////////////////////////////////////////////////




//Reads in file
val lines = sc.textFile("File:///home/cloudera/Desktop/data files/data files/twitter/1millionTweets.tsv")

//splits lines via tab space delimiters
val split_lines = lines.map(_.split("\t"))

use map to take out hashtag name and count, reduce my adding the values (count) in this tuple, sort from largest to smallest then take the first result(one with biggest count) 
split_lines.map(x => (x(3), x(2).toInt)).reduceByKey(_ + _).sortBy(x=> x._2, false).take(1)