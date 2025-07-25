# ☕ Java OOP & SOLID 스터디 - study 프로젝트

이 프로젝트는 **객체지향 설계 원칙(OOP & SOLID)** 을 중심으로,  
Java 기반 예제를 실습하며 개념을 적용해보는 스터디용 저장소입니다.

---

## 📚 학습 목표

- 객체지향 4대 특성 (추상화, 캡슐화, 상속, 다형성)
- SOLID 원칙 (SRP, OCP, LSP, ISP, DIP)
- 추상 클래스 vs 인터페이스 이해
- 결합도 낮은 설계 및 확장 가능한 구조 구성법

---

## 🧩 예제 구성

현재는 다음과 같은 예제를 포함하고 있으며, 추후 과제가 추가될 수 있습니다:

- `ordersystem`: 상품 주문 시스템 예제
    - 상품(Product), 결제(Payment), 주문(Order) 도메인을 다룸
    - SOLID 원칙과 OOP 개념이 전반적으로 적용됨

---

## 🛠 참여 방식

1. `main` 브랜치 기준으로 **개인 브랜치 생성**
   ```bash
   git checkout -b feat/이름
   
--- 

## 🧠 스터디 풀이 가이드

- 클래스 상단에 주어진 `/** ✅ 미션: ... */` 주석을 기반으로 구현합니다.
- 요구사항 외에도 **추상화 / 확장성 / 테스트 가능성** 등을 염두에 두고 구조를 설계해보세요.
- 정답보다도 **SRP / OCP / DIP** 등을 고려한 구조와 **고민의 흔적**을 중점으로 리뷰합니다.
- 필요 시 `@Override`, `@FunctionalInterface`, `Logger`, `Enum`, `Stream` 사용도 자유롭게 가능
- **AI 도구(GPT 등)는 학습 참고용으로 사용 가능합니다.**  
    단, **코드 전체를 대신 작성하거나 그대로 복붙하는 방식의 사용은 지양합니다.**  
    본인의 고민과 설계가 반드시 반영되어야 합니다.