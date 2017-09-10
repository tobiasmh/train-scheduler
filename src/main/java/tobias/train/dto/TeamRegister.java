package tobias.train.dto;


import java.util.Collection;

public class TeamRegister {

    private String name;

    private String url;

    private Collection<String> members;

    public TeamRegister(String name, String url, Collection<String> members) {
        this.name = name;
        this.url = url;
        this.members = members;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Collection<String> getMembers() {
        return members;
    }

    public void setMembers(Collection<String> members) {
        this.members = members;
    }
}
