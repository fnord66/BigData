/////////////////////////////////////////////////////////////////////////////////////////////
//THIS CODE READS IN TWITTER DATA IN THE FORMAT 
//"(TYPE (eg Hashtag)) (MONTH (eg 200908)) (COUNT (integer)) (Hashtag name(eg #LOL $YOLO))"
//
//Find row that has the highest count, report month, count and hashtag
////////////////////////////////////////////////////////////////////////////////////////////


//Reads in file
val lines = sc.textFile("File:///home/cloudera/Desktop/data files/data files/twitter/1millionTweets.tsv")

//splits lines via tab space delimiters
val split_lines = lines.map(_.split("\t"))

//first map => (go through and take month, hashtag name and count and convert to int.), next reduce =>(all 3 tuples by finding the max ammount among them all), sort this list by count and take the first element 
split_lines.map(x => ((x(1), x(3)), x(2).toInt)).reduceByKey((x, y) => Math.max(x, y)).sortBy(x=> x._2, false).take(1)