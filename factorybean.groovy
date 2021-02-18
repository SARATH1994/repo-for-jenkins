VCSF vcsf = new VCSF()  //Returns VCSF obj

//VCS git = vcsf.getInstance("gitsdas")

VCS svn = vcsf.getInstance("svnfs")  //geeting Obj based on name 

//Gitone one = new Gitone()

Svnone SVN = new Svnone()  // Instantiate class that you have 

svn.checkout(SVN,"Sarath")  // passing obj to method 


//git.checkout()
 
class VCSF  {
    
     VCS getInstance(str) {
         if (str.contains("git"))
         return new git()
         if (str.contains("svn"))
         return new svn()
     }
}




interface VCS {                                         

    void checkout()
    
    //void checkout(obj,name)
}

class git implements VCS {
    
    void checkout() {
        println("original git")
    }
    
    void checkout(obj,name) {
        obj.checkout(name)
    
    }
    
    
}

class svn implements VCS {
    
    void checkout() {
        println "I am from SVN"
    }
    
    void checkout(obj,name) {
        obj.checkout(name)
    
    }
}

class Svnone {
    
    void checkout(name) {
        println "I am checkout from Svnone "+name
    }
}

class Gitone {
    
    void checkout() {
        println "I am checkout from Gitone "
    }
}

class Gittwo {
    
    void checkout() {
        println "I am checkout from Gittwo "
    }
}


