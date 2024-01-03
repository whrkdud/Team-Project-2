// 수량에따라 주문금액 표기 -> 총(주문금액)결제하기 표기

function calculateButton() {
    var productRows = document.querySelectorAll('.cartListDetail'); // 상품 행 요소 가져오기
    var total = 0;

    // 상품 행의 주문 금액 계산
    productRows.forEach(function(row) {
        var count = parseInt(row.querySelector('.count').innerText); // 수량
        var priceText = row.querySelector('.price').innerText; // 가격 텍스트 
        var price = parseInt(priceText.replace(/[^0-9]/g, '')); // 가격 

        var orderPrice = count * price; 
        total += orderPrice; 

        row.querySelector('.price').innerText = orderPrice.toLocaleString() + '원'; 
    });

    // 총 주문금액을 표시하는 요소 가져와서 값 업데이트
    var orderBtn = document.querySelector('.cartBigorderbtn');
    orderBtn.innerText = `총 ${total.toLocaleString()}원 결제하기`;

    // 모달 쪽 총 금액 설정
    totalprice = document.querySelector(".totalprice");
    totalprice.innerText += `${total.toLocaleString()}원`;
}

// 수량이 변경될 때마다 calculateButton 함수 호출
var counts = document.querySelectorAll('.count');
counts.forEach(function (count) {
    count.addEventListener('input', calculateButton);
});

// 초기화: 페이지 로딩 시 총 주문금액 계산
calculateButton();