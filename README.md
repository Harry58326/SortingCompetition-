# SortingCompetition-

sumPrimeFactors method is used for finding the sum of prime factors for data point in the file. Hashmap is used for quick lookups. 

Sort method calls sumPrimeFactors method for quick lookups. The sorting satisfies the requirements. 


It is interesting that we use hash map for quick lookups. It really speeds up the sorting process and can also handle duplicate value as well. 

We believe our method was implemented in a reasonable way. In the Arrays.sort() we overrode comparator so that it works the way we want. 

Because we use hashmap to keep track of value we have visited, we can easily skip duplicate value with the code below, 
which n stands for the value we want to check its sum of prime factors. 

if (temp.containsKey(n)) {
       return;
}


