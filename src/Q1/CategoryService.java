package Q1;

import java.util.*;

/**
 * 게시판 카테고리 자료구조
 */
public class CategoryService {

    final List<Category> categories;

    public CategoryService(){
        categories = new ArrayList<>();
    }

    public void save(Category category){
        category.setCategoryId((long) (categories.size()+1));
        categories.add(category);
    }

    /**
     * 삭제
     * 1. 삭제 하려는 카테고리 식별자를 하위 카테고리 식별자 리스트에서 제거
     * 2. 카테고리 제거
     *
     * @param category
     */
    public void delete(Category category){
        categories.stream().forEach(data -> {
            if(!Objects.isNull(data.getChildIds()))
                data.getChildIds().removeIf(childId -> childId == category.getCategoryId());

        });
        categories.remove(category);
    }

    /**
     * 전체 조회
     *
     * 1. 최상위 카테고리 찾기(filter)
     * 2. 해당 카테고리의 하위 카테고리 차례로 조회
     *
     * @return
     */
    public List<LinkedHashMap<String, Object>> findAll(){
        List<LinkedHashMap<String, Object>> response = new LinkedList<>();
        categories.stream()
                .filter(category -> isTop(category))
                .forEach(category -> response.add(findChildren(category)));

        return response;
    }

    /**
     * 최상위 카테고리 여부 판단
     *
     * 조건) 하위 카테고리를 갖고, 다른 카테고리의 하위로 속하지 않은 카테고리
     *
     * @param category
     * @return
     */
    public boolean isTop(Category category){
        return !Objects.isNull(category.getChildIds())
                && categories.stream().filter(c -> !Objects.isNull(c.getChildIds()))
                .noneMatch(cate -> cate.getChildIds().stream()
                        .anyMatch(id -> id == category.getCategoryId()));
    }

    /**
     * 하위 카테고리 조회
     *
     * @param category
     * @return
     */
    public LinkedHashMap<String, Object> findChildren(Category category){
        LinkedHashMap<String, Object> data = new LinkedHashMap<>();
        data.put("categoryId", category.getCategoryId());
        data.put("categoryName", category.getName());

        // 하위 카테고리의 하위 카테고리 조회
        if(!Objects.isNull(category.getChildIds())) {
            List<Object> children = new LinkedList<>();
            category.getChildIds().forEach(id -> findOne(id).ifPresent(child -> children.add(findChildren(child))));
            data.put("children", children);
        }
        return data;
    }

    /**
     * 식별자로 조회
     *
     * @param categoryId
     * @return
     */
    public List<LinkedHashMap<String, Object>> findAllById(Long categoryId){
        List<LinkedHashMap<String, Object>> response = new LinkedList<>();
        findOne(categoryId)
                .ifPresent(category -> response.add(findChildren(category)));

        return response;
    }

    /**
     * 카테고리 명으로 조회
     * @param categoryName
     * @return
     */
    public List<LinkedHashMap<String, Object>> findAllByName(String categoryName){
        List<LinkedHashMap<String, Object>> response = new LinkedList<>();
        findOne(categoryName)
                .ifPresent(category -> response.add(findChildren(category)));

        return response;
    }

    public Optional<Category> findOne(Long categoryId){
        return categories.stream()
                .filter(category -> category.getCategoryId() == categoryId)
                .findFirst();
    }

    public Optional<Category> findOne(String categoryName){
        return categories.stream()
                .filter(category -> category.getName().equals(categoryName))
                .findFirst();
    }
}
