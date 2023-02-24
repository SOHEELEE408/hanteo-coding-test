package Q1;

import java.util.ArrayList;
import java.util.List;

/**
 * [1번 문제]
 *
 * 게시판 카테고리 객체
 */
public class Category {

    /**
     * 카테고리 식별자
     */
    private Long categoryId;

    /**
     * 카테고리 명
     */
    private String name;

    /**
     * 하위 카테고리 식별자
     */
    private List<Long> childIds = new ArrayList<>();


    public Category(String name){
        this.name = name;
    }

    public Category(String name, List<Long> childIds){
        this.name = name;
        this.childIds = childIds;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId){
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public List<Long> getChildIds() {
        return childIds;
    }


}
