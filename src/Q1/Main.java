package Q1;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

/**
 * [2번 문제]
 *
 * 입력 예시)
 *
 * 조회 방식을 선택해주세요. (1: 전체, 2: 이름, 3: 카테고리 식별자) : 2
 * 검색어를 입력해주세요. : 엑소
 *
 * 조회 방식을 선택해주세요. (1: 전체, 2: 이름, 3: 카테고리 식별자) : 3
 * 카테고리 식별자를 입력해주세요.: 10
 * (저장되는 데이터 수가 14이므로 1~14까지 입력 가능합니다.)
 *
 */
public class Main {

    public static void main(String[] args) {
        ObjectMapper objectMapper = new ObjectMapper();
        CategoryService service = new CategoryService();
        saveData(service);

        String response = null;
        Scanner sc = new Scanner(System.in);
        System.out.print("조회 방식을 선택해주세요. (1: 전체, 2: 이름, 3: 카테고리 식별자) : ");
        int type = Integer.parseInt(sc.nextLine());

        try {
            if(type == 1){
                response = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(service.findAll());

            } else if(type == 2){
                System.out.println("검색어를 입력해주세요. : ");
                String word = sc.nextLine();
                response = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(service.findAllByName(word));

            } else if(type == 3) {
                System.out.println("카테고리 식별자를 입력해주세요.: ");
                Long id = Long.parseLong(sc.nextLine());
                if (id < service.categories.size() && id > 0) response = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(service.findAllById(id));
            }

            if(Objects.isNull(response)) System.out.println("조회할 수 있는 데이터가 없습니다.");
            else System.out.println(response);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }


    }

    /**
     * 데이터 저장
     *
     * @param service
     */
    public static void saveData(CategoryService service){
        Category notice1 = new Category("공지사항");
        service.save(notice1);

        Category chen = new Category("첸");
        service.save(chen);

        Category baekhyun = new Category("백현");
        service.save(baekhyun);

        Category xiumin = new Category("시우민");
        service.save(xiumin);

        Category notice2 = new Category("공지사항");
        service.save(notice2);

        Category anonymous = new Category("익명게시판");
        service.save(anonymous);

        Category v = new Category("뷔");
        service.save(v);

        Category notice3 = new Category("공지사항");
        service.save(notice3);

        Category rose = new Category("로제");
        service.save(rose);

        Category exo = new Category("엑소",
                new ArrayList<>(Arrays.asList(notice1.getCategoryId(), chen.getCategoryId(), baekhyun.getCategoryId(), xiumin.getCategoryId())));
        service.save(exo);

        Category bts = new Category("방탄소년단",
                new ArrayList<>(Arrays.asList(notice2.getCategoryId(), anonymous.getCategoryId(), v.getCategoryId())));
        service.save(bts);

        Category blackpink = new Category("블랙핑크",
                new ArrayList<>(Arrays.asList(notice3.getCategoryId(), anonymous.getCategoryId(), rose.getCategoryId())));
        service.save(blackpink);

        Category male = new Category("남자",
                new ArrayList<>(Arrays.asList(exo.getCategoryId(), bts.getCategoryId())));
        service.save(male);

        Category female = new Category("여자",
                new ArrayList<>(Arrays.asList(blackpink.getCategoryId())));
        service.save(female);

        service.delete(blackpink);
    }
}
