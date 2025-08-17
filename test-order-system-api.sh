#!/bin/bash

# 기존 디렉토리를 활용한 미니 쇼핑몰 주문 시스템 API 테스트 스크립트
BASE_URL="http://localhost:8080/api"

echo "=== 기존 디렉토리를 활용한 미니 쇼핑몰 주문 시스템 API 테스트 시작 ==="

# 1. 회원 등록
echo "1. 회원 등록 테스트"
USER1_RESPONSE=$(curl -s -X POST $BASE_URL/users \
  -H "Content-Type: application/json" \
  -d '{
    "name": "홍길동",
    "email": "hong@example.com"
  }')
echo "응답: $USER1_RESPONSE"
USER1_ID=$(echo $USER1_RESPONSE | jq -r '.data')

echo ""

# 2. 두 번째 회원 등록
echo "2. 두 번째 회원 등록 테스트"
USER2_RESPONSE=$(curl -s -X POST $BASE_URL/users \
  -H "Content-Type: application/json" \
  -d '{
    "name": "김철수",
    "email": "kim@example.com"
  }')
echo "응답: $USER2_RESPONSE"
USER2_ID=$(echo $USER2_RESPONSE | jq -r '.data')

echo ""

# 3. 회원 목록 조회
echo "3. 회원 목록 조회 테스트"
curl -s -X GET $BASE_URL/users | jq '.'

echo ""

# 4. 상품 등록 (BOOK)
echo "4. 책 상품 등록 테스트"
BOOK_RESPONSE=$(curl -s -X POST $BASE_URL/products \
  -H "Content-Type: application/json" \
  -d '{
    "name": "JPA 프로그래밍",
    "price": 30000,
    "stockQuantity": 100,
    "productType": "BOOK"
  }')
echo "응답: $BOOK_RESPONSE"
BOOK_ID=$(echo $BOOK_RESPONSE | jq -r '.data')

echo ""

# 5. 상품 등록 (FOOD)
echo "5. 음식 상품 등록 테스트"
FOOD_RESPONSE=$(curl -s -X POST $BASE_URL/products \
  -H "Content-Type: application/json" \
  -d '{
    "name": "신선한 사과",
    "price": 5000,
    "stockQuantity": 50,
    "productType": "FOOD"
  }')
echo "응답: $FOOD_RESPONSE"
FOOD_ID=$(echo $FOOD_RESPONSE | jq -r '.data')

echo ""

# 6. 상품 등록 (ELECTRONICS)
echo "6. 전자제품 상품 등록 테스트"
ELECTRONICS_RESPONSE=$(curl -s -X POST $BASE_URL/products \
  -H "Content-Type: application/json" \
  -d '{
    "name": "스마트폰",
    "price": 800000,
    "stockQuantity": 10,
    "productType": "ELECTRONICS"
  }')
echo "응답: $ELECTRONICS_RESPONSE"
ELECTRONICS_ID=$(echo $ELECTRONICS_RESPONSE | jq -r '.data')

echo ""

# 7. 상품 목록 조회
echo "7. 상품 목록 조회 테스트"
curl -s -X GET $BASE_URL/products | jq '.'

echo ""

# 8. 상품 종류별 조회
echo "8. 상품 종류별 조회 테스트 (BOOK)"
curl -s -X GET "$BASE_URL/products/type/BOOK" | jq '.'

echo ""

# 9. 주문 생성
echo "9. 주문 생성 테스트"
ORDER_RESPONSE=$(curl -s -X POST $BASE_URL/orders \
  -H "Content-Type: application/json" \
  -d '{
    "userId": '$USER1_ID',
    "orderItems": [
      {
        "productId": '$BOOK_ID',
        "quantity": 2
      },
      {
        "productId": '$FOOD_ID',
        "quantity": 5
      }
    ]
  }')
echo "응답: $ORDER_RESPONSE"
ORDER_ID=$(echo $ORDER_RESPONSE | jq -r '.data')

echo ""

# 10. 주문 내역 조회
echo "10. 주문 내역 조회 테스트"
curl -s -X GET "$BASE_URL/orders/user/$USER1_ID" | jq '.'

echo ""

# 11. 주문 상태별 조회
echo "11. 주문 상태별 조회 테스트 (ORDERED)"
curl -s -X GET "$BASE_URL/orders/search/status?orderStatus=ORDERED" | jq '.'

echo ""

# 12. 주문 취소
echo "12. 주문 취소 테스트"
curl -s -X POST "$BASE_URL/orders/$ORDER_ID/cancel" | jq '.'

echo ""

# 13. 취소 후 주문 내역 확인
echo "13. 취소 후 주문 내역 확인"
curl -s -X GET "$BASE_URL/orders/user/$USER1_ID" | jq '.'

echo ""

# 14. 기간별 주문 검색
echo "14. 기간별 주문 검색 테스트"
START_DATE=$(date -u +"%Y-%m-%dT00:00:00")
END_DATE=$(date -u +"%Y-%m-%dT23:59:59")
curl -s -X GET "$BASE_URL/orders/search/date?startDate=$START_DATE&endDate=$END_DATE" | jq '.'

echo ""
echo "=== 기존 디렉토리를 활용한 미니 쇼핑몰 주문 시스템 API 테스트 완료 ==="
