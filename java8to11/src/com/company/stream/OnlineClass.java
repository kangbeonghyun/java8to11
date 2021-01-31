package com.company.stream;

import java.util.Optional;

public class OnlineClass {
    private Integer id;

    private String title;

    private boolean closed;



    public Progress progress;

    public OnlineClass(Integer id, String title, boolean closed) {
        this.id = id;
        this.title = title;
        this.closed = closed;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }

//    public Progress getProgress() {
//        return progress;
//    }
    public Optional<Progress> getProgress() {//Optional은 주로 리턴타입으로 활용하는 것이 권장됨,파라미터로 줄수 있는데 널을 파라미터로 줄수있기 때문에 골치아파짐.
        return Optional.ofNullable(progress);//progress는 널일 수 있다.=optional안에 비어있는거와 같
    }//만약 진짜 리턴할게 없는 경우 null을 리턴하지 말고 Optional.empty()로 해서 optional을 리턴하자.

    public void setProgress(Progress progress) {
        this.progress = progress;
    }
}
