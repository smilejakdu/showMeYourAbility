package com.example.showmeyourability.Field.domain;

import com.example.showmeyourability.shared.Enum.FieldType;
import lombok.Value;

@Value // Lombok의 @Value 어노테이션은 클래스를 불변 클래스로 만듭니다. 이는 값 객체에 적합합니다.
public class FieldTypeVo {
    FieldType fieldType;       // 예: "축구장", "농구장"
}
