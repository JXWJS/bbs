package online.xuanwei.bbs.dto;

import lombok.Data;

@Data
public class GithubUser {

    private String name;
    private long id;
    private String bio;
    private String avatar_url;

    public GithubUser(){}
    public GithubUser(String name, long id, String bio) {
        this.name = name;
        this.id = id;
        this.bio = bio;
    }

}
