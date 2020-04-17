class A {
    void methodA() {              //Upon compilation class A file generates
        println "in methodA"
    }
}

class B {
    void methodB() {              //Upon compilation class B file generates
        println "in methodB"
    }
}

class C {
    void methodC() {            //Upon compilation class C file generates
        println "in methodC"
    }
}

new A().methodA()              //The code outside of class is known as script
new B().methodB()
new C().methodC()   


$groovy main.groovy

---------------Output-----------------------
in methodA
in methodB
in methodC
