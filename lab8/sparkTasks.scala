
////////////////////TASK A


//creates a RDD from a scala list
val manyNumbers = sc.parallelize(1 to 10000)
//takes each number multiples by 5 and shows only those abouve 5000
val largeNumbers = manyNumbers.map(_ * 5).filter(_ > 5000)

///////////////////////////////////////////TASK B

val animalAges = sc.parallelize(List(("cat", 7), ("dog", 5), ("monkey", 3), ("cat", 6), ("dog", 10), ("bird", 1), ("bird",1)))
//reduce by key returns key-value pairs with all animals of the same type with ages added together
val result = animalAges.reduceByKey(_ + _)
//this makes spark print collection to the screen
result.collect


//this is similar to reduce by key but instead groups items with same key and value is a collection of all values
val result = animalAges.groupByKey()


val animalLegs = sc.parallelize(List(("monkey", 2), ("dog", 4), ("cat", 4), ("bird", 2)))
//this joins the animal ages and animal legs
val result = animalAges.join(animalLegs)
//RESULT!!!
//Array((monkey,(3,2)), (dog,(5,4)), (dog,(10,4)), (bird,(1,2)), (bird,(1,2)), (cat,(7,4)), (cat,(6,4)))

//sorts animal list by their ages (x._2), true means ascending order
val sortedAnimals = animalAges.sortBy(x => x._2, true)


//load text file 
val lines = sc.textFile("File:///home/cloudera/Desktop/lab8/census.txt")
//split lines by ', '
val split_lines = lines.map(_.split(", "))
split_lines.first()

//create tuples of native country and occupations
val countryOccupation = split_lines.map(record => (record(13), record(6)))
countryOccupation.saveAsTextFile("File:///home/cloudera/Desktop/lab8/countryOccupation")









//takes a collection (a), finds the square root of each number and adds all the results together 
a.map(x => Math.sqrt(x)).reduce(_ + _)

//maps second element and third element of tuples adds the corresponding 
val people = sc.parallelize(Array(("Jane", "student", 1000), ("Peter", "doctor", 100000), ("Mary", "doctor", 200000), ("Michael", "student", 1000)))
res = people.map(x => (x._2, x._3)).reduceByKey(_ + _).sortBy(x => x._1, true)

