//Closure defination 

def clos = {param->println "Hello ${param}"};

//map defination
def map = [a:1,b:3]


//calling method which works on map & closure
m1(map,clos)
m2(map)

//method defination 

def m1(map,clos) {
  clos(map)
}

def m2(map1){
    map1.c = "8"
}

println map.getClass()
