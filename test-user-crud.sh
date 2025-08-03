#!/bin/bash

# User CRUD API 테스트 스크립트
BASE_URL="http://localhost:8080/users"

echo "=== User CRUD API 테스트 시작 ==="

# 1. 회원 등록 (과제 목표 1)
echo "1. 회원 등록 테스트"
USER1_RESPONSE=$(curl -s -X POST $BASE_URL \
  -H "Content-Type: application/json" \
  -d '{
    "email": "user1@example.com",
    "password": "password123",
    "name": "홍길동"
  }')
echo "응답: $USER1_RESPONSE"
USER1_ID=$(echo $USER1_RESPONSE | tr -d '"')

echo ""

# 2. 두 번째 회원 등록
echo "2. 두 번째 회원 등록 테스트"
USER2_RESPONSE=$(curl -s -X POST $BASE_URL \
  -H "Content-Type: application/json" \
  -d '{
    "email": "user2@example.com",
    "password": "password456",
    "name": "김철수"
  }')
echo "응답: $USER2_RESPONSE"

echo ""

# 3. 회원 목록 조회 (과제 목표 2)
echo "3. 회원 목록 조회 테스트"
curl -s -X GET $BASE_URL | jq '.'

echo ""

# 4. 특정 회원 조회
echo "4. 특정 회원 조회 테스트 (ID: $USER1_ID)"
curl -s -X GET "$BASE_URL/$USER1_ID" | jq '.'

echo ""

# 5. 회원 정보 수정 (과제 목표 3)
echo "5. 회원 정보 수정 테스트 (PUT)"
curl -s -X PUT "$BASE_URL/$USER1_ID" \
  -H "Content-Type: application/json" \
  -d '{
    "email": "user1_updated@example.com",
    "password": "newpassword123",
    "name": "홍길동(수정됨)"
  }'
echo " - 수정 완료"

echo ""

# 6. 수정된 회원 정보 확인
echo "6. 수정된 회원 정보 확인"
curl -s -X GET "$BASE_URL/$USER1_ID" | jq '.'

echo ""

# 7. 회원 이름만 수정 (과제 목표 4)
echo "7. 회원 이름만 수정 테스트 (PATCH)"
curl -s -X PATCH "$BASE_URL/$USER1_ID" \
  -H "Content-Type: application/json" \
  -d '"홍길동(이름만수정)"'
echo " - 이름 수정 완료"

echo ""

# 8. 이름 수정 후 확인
echo "8. 이름 수정 후 확인"
curl -s -X GET "$BASE_URL/$USER1_ID" | jq '.'

echo ""

# 9. 회원 삭제 (과제 목표 5)
echo "9. 회원 삭제 테스트"
curl -s -X DELETE "$BASE_URL/$USER1_ID"
echo " - 삭제 완료"

echo ""

# 10. 삭제 후 목록 확인
echo "10. 삭제 후 회원 목록 확인"
curl -s -X GET $BASE_URL | jq '.'

echo ""

# 11. 삭제된 회원 조회 시도 (에러 확인)
echo "11. 삭제된 회원 조회 시도 (에러 확인)"
curl -s -X GET "$BASE_URL/$USER1_ID"

echo ""
echo "=== User CRUD API 테스트 완료 ===" 