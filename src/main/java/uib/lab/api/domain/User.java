package uib.lab.api.domain;

public class User {
    private long id;

    private String username;
    private String name;
    private String password;

    public User(long id, String u, String n, String p){
        this.id = id;
        this.username = u;
        this.name = n; 
        this.password = p;
    }

    public User(String u, String n, String p){
        this.username = u;
        this.name = n; 
        this.password = p;
    }

    public User(){
        
    }

    public long getId(){
        return this.id;
    }

    public String getUsername(){
        return this.username;
    }

    public String getName(){
        return this.name;
    }

    public String getPassword(){
        return this.password;
    }


}
