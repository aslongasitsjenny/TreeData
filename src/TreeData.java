
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;

class TreeData {

    private String mostCommonAge;

    private Map<String, Double> averageHeightPerTreeName; //private map object in Java that stores the average height values of trees based on their names as keys
    private ArrayList<Tree> trees; // maintain a collection of Tree objects

    public TreeData(String fileName) {
        trees = new ArrayList<>();//trees is being initialised as a new instance of the ArrayList class, ArrayList object that can store objects of any type, sets up an empty collection for storing Tree objects.
        loadDataFromFile("C:\\Users\\User\\Desktop\\treesPruned\\treesPruned.csv");
    }

    private void loadDataFromFile(String fileName) {
        try {
            FileInputStream fis = new FileInputStream(fileName); // read the contents of a file identified by the fileName
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));// This means that br is set up to read text from the fis input stream which represents the contents of a file identified by the fileName variable

            String line; //declares a variable named line of type String that will be used to store each line read from the input file during data loading
            while ((line = br.readLine()) != null) { //reads each line from the input file using the BufferedReader object br, and continues looping as long as there are still lines to be read and that theyre not null
                String[] treeData = line.split(","); //splits the current line line into an array of strings, using comma as the delimiter
                if (treeData.length != 5) { // Skip if the line is not of the correct format
                    continue;
                }
                int objectId; // declares a variable named objectId of type int that will be used to store the objectId
                try {
                    objectId = Integer.parseInt(treeData[0]); // try to parse the objectId from the current line
                } catch (NumberFormatException e) { // If the objectId cannot be parsed, skip the line
                    continue; // Continue to the next line
                }
                String borough = treeData[1].trim(); //trim is used to eliminate any extra spaces that may be present before or after the actual data
                String treeName = treeData[2];
                String ageGroup = treeData[3];
                double heightM = Double.parseDouble(treeData[4]); //converts the string at index 4 in the treeData array into a double value

                Tree tree = new Tree(objectId, borough, treeName, ageGroup, heightM); //. it initialises a new instance of the Tree class with the provided values which are then added to the ArrayList trees
                trees.add(tree); //adds the created tree object to the trees ArrayList, which is a collection of Tree objects. it appends the tree object to the end of the list

            }

            br.close(); // closes the BufferedReader object
        } catch (IOException e) { // If there is an error reading the file, print an error message
            e.printStackTrace();// print an error message
        }
    }



    public void printTreesPerBorough() {
        ArrayList<String> boroughs = new ArrayList<>(); //  declares and initialises a new ArrayList object named boroughs that can store elements of type String
        ArrayList<Integer> treeCounts = new ArrayList<>(); //  declares and initialises a new ArrayList object named treeCounts that can store elements of integer type

        // Counting trees per borough
        for (Tree tree : trees) { //for-each loop that iterates over each element in the trees ArrayList, where each element is of type Tree
            String borough = tree.getBorough(); // get the borough from the tree object
            if (borough == null) { // Skip if borough is null
                continue; // Continue to the next element
            }
            if (boroughs.contains(borough)) { // If the borough is already in the boroughs ArrayList, skip it
                int index = boroughs.indexOf(borough); // get the index of the borough in the boroughs ArrayList
                treeCounts.set(index, treeCounts.get(index) + 1); //updates the value at the given index of the treeCounts ArrayList by incrementing it by 1.
            } else {
                boroughs.add(borough); //adds the borough value to the boroughs ArrayList.
                treeCounts.add(1); //adds the value 1 to the treeCounts ArrayList.
            }
        }

        // Printing trees per borough
        System.out.println("Number of Trees per Borough:");
        for (int i = 0; i < boroughs.size(); i++) { //for loop that iterates over the elements of the boroughs ArrayList, from index 0 to the size of the ArrayList minus 1, using the loop variable i as the index
            String borough = boroughs.get(i);//Fetches borough name at index i from boroughs arraylist, to fetch the borough name for printing in output.
            int treeCount = treeCounts.get(i); //Fetches the tree count for the current borough from the treeCounts ArrayList, using the loop variable i as the index.
            System.out.println(borough + "=" + treeCount); //Prints the borough name and the number of trees in that borough.
        }
    }



    public void printMostCommonAge() {
        Map<String, Integer> ageCounts = new HashMap<>(); //  declares and initialises a new HashMap object named ageCounts that can store elements of integer
        for (Tree tree : trees) { //for-each loop that iterates over each element in the trees ArrayList, where each element is of type Tree
            String age = tree.getAge(); //fetches the age of the current tree object using the getAge() method and assigns it to a String variable called age
            if (age != null) { // Skip if age is null
                ageCounts.put(age, ageCounts.getOrDefault(age, 0) + 1); //updates the ageCounts map by incrementing the count for the age key.
            }
        }

        mostCommonAge = ""; //declare and initialise a String variable called mostCommonAge
        int maxCount = 0; //declare and initialise an int variable called maxCount
        for (Map.Entry<String, Integer> entry : ageCounts.entrySet()) { //Iterates over the entries of the ageCounts map, where each entry consists of a key-value pair representing an age and its corresponding count.
            // The loop variable entry is used to access each key-value pair in the map during each iteration of the loop.^
            String age = entry.getKey(); // retrieves the key from the current key-value pair during each iteration of the loop and assigns it to a String variable called "age".
            int count = entry.getValue(); //retrieves the value from the current key-value pair during each iteration of the loop and assigns it to an integer variable called "count".
            if (count > maxCount) {
                mostCommonAge = age;
                maxCount = count;
            }
        }

        System.out.println(mostCommonAge);
    }

    public void printLeastCommonAge() {
        Map<String, Integer> ageCounts = new HashMap<>(); //  declares and initialises a new HashMap object named ageCounts that can store elements of integer
        String leastCommonAge = String.valueOf(Integer.MIN_VALUE); // initialises a String variable with the minimum value of an integer, converted to a String. This is used as a default value for the least common age
        int leastCommonCount = Integer.MAX_VALUE; // initialises an integer variable called "leastCommonCount" with the maximum value of an integer. This is used as a default value for the count of the least common age

        // count the number of trees for each age
        for (Tree tree : trees) { //for-each loop that iterates over each element in the trees ArrayList, where each element is of type Tree
            String age = tree.getAge(); // fetches the age of the current tree object using the "getAge()" method and assigns it to a String variable called "age".
            int count = ageCounts.getOrDefault(age, 0) + 1; //retrieves the current count for the "age" key from the "ageCounts" map, and if the key is not present, it returns 0 as the default value.
            //It then increments the retrieved count by 1.^^
            ageCounts.put(age, count); //updates the "ageCounts" map with the current "age" key and its corresponding "count" value
        }

        // find the least common age
        for (Map.Entry<String, Integer> entry : ageCounts.entrySet()) {// iterate over the entries (key-value pairs) in the "ageCounts" map.
            //^for-each loop with a loop variable "entry" < key-value pair of type "Map.Entry<String, Integer>", where "String" represents the key type and integer represents the value type of the map^^
            String age = entry.getKey(); //"Gets key" from "entry" in "ageCounts" map and assigns it to "age". Allows age access in loop.
            int count = entry.getValue(); //gets  the value from the current entry within the "ageCounts" map and assigns it to an integer variable named "count"
            if (count < leastCommonCount) { // if the current count is less than the least common count
                leastCommonAge = age;
                leastCommonCount = count;
            }
        }

        // Print the least common age and count
        System.out.println(" (" + leastCommonAge + ")=" + leastCommonCount);
    }


    public void printAverageHeightForTree(String treeName) {
        String name = treeName.toLowerCase(); // declares and initialises a String variable called name and saves it to treeName in lowercase
        double totalHeight = 0; // declares and initialises a double variable called totalHeight
        int treeCount = 0; // declare and initialises an int variable called treeCount, information about how many times a specific tree name appears
        for (Tree tree : trees) { //for-each loop that iterates over each element in the trees ArrayList, where each element is of type Tree
            if (tree.getName().equalsIgnoreCase(name)) { //checks if the name of a tree object is equal to the value of the variable name without considering the case of the characters
                totalHeight += tree.getHeight(); //the height of each tree is then added to the running total called totalHeight
                treeCount++; // increments the treeCount by 1
            }
        }
        if (treeCount > 0) {
            double averageHeight = totalHeight / treeCount;
            System.out.println("Average height for " + treeName + ": " + averageHeight);
        } else {
            System.out.println("No trees found for " + treeName);
        }
    }




    public void printAverageHeightPerTreeName() {
        averageHeightPerTreeName = new HashMap<>(); /// declares and initialises a new HashMap object named averageHeightPerTreeName that can store elements
        Map<String, Integer> treeCounts = new HashMap<>(); //
        for (Tree tree : trees) { //for-each loop that iterates over each element in the trees ArrayList, where each element is of type Tree
            String name = tree.getName(); // fetches the name of the current tree object using the "getName()" method and assigns it
            double height = tree.getHeight(); // fetches the height of the current tree object using the "getHeight()" method and assigns it
            if (averageHeightPerTreeName.containsKey(name)) { // If the tree name is already in the averageHeightPerTreeName HashMap, skip it
                averageHeightPerTreeName.put(name, averageHeightPerTreeName.get(name) + height); //updates the averageHeightPerTreeName HashMap by adding the height of the tree to the averageHeight
                treeCounts.put(name, treeCounts.get(name) + 1);
            } else {
                averageHeightPerTreeName.put(name, height);
                //adds a key-value pair to a map called averageHeightPerTreeName; key is given by the variable name, the value is given by the variable height, associates the name with the corresponding height value in the averageHeightPerTreeName map
                treeCounts.put(name, 1); // adds a key-value pair  to treesCounts, the key is given by the variable name, and the value is set to 1.
            }
        }

        for (String treeName : averageHeightPerTreeName.keySet()) { //iterates through each key (tree name) in the averageHeightPerTreeName 1by1, temporarily assigns it to a variable called treeName
            double averageHeight = averageHeightPerTreeName.get(treeName) / treeCounts.get(treeName);
            //calculates the average height of a tree by dividing the average height value associated with a specific treeName from the averageHeightPerTreeName collection by the count value associated with the same treeName from the treeCounts collection.
            System.out.println(treeName + "=" + averageHeight);
        }
    }

}