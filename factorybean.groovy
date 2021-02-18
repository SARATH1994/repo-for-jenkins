VCSF vcsf = new VCSF()
VCS vcs = vcsf.getInstance("svnvv")
vcs.checkout()


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
}

class git implements VCS {
    @Override
    void checkout() {
        println "I am from git"
    }
}

class svn implements VCS {
    @Override
    void checkout() {
        println "I am from SVN"
    }
}

