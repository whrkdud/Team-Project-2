// 로고를 누르면 main으로 이동 //
const logo = document.querySelector("#logo > img");
logo.addEventListener("click", function() {
    location.href="/main";
});

// 상세검색을 누르면 상세검색 모달창 띄우기 //
const modal = document.querySelector("#checkBox");
const modalButton = document.querySelector("#detailSearch");
const blur = document.querySelector("#allWrap");
modalButton.addEventListener("click", function() {
    modal.style.display = "block";
    blur.style.opacity = "0.5";
});

// 상세검색 모달창 닫기 버튼 //
const close = document.querySelector("#checkBoxX > p");
close.addEventListener("click", function() {
    modal.style.display = "none";
    blur.style.opacity = "1";
});

// 결제하기 버튼을 누르면 구매완료 모달창 띄우기 //
// const modal2 = document.querySelector("#buyO");
// const modalButton2 = document.querySelector(".cartBigorderbtn");
// modalButton2.addEventListener("click", function() {
//     modal2.style.display = "block";
//     blur.style.opacity = "0.5";
// });

// 구매내역 insert를 위한 모달창 띄우기 재설정 //
const modal2 = document.querySelector("#buyO");
const checkmodal = document.querySelector(".modal");
if (checkmodal != null) {
    if (checkmodal.innerHTML == "O") {
    modal2.style.display = "block";
    blur.style.opacity = "0.5";
}}

const cartmsg = document.querySelector(".cartmsg")
if (cartmsg != null) {
    const seqinput = document.querySelectorAll(".seqinput");
    const qtyinput = document.querySelectorAll(".qtyinput");
    for (let i = 0; i < seqinput.length; i++) {
        seqinput[i].setAttribute("name","seq"+[i]);
        qtyinput[i].setAttribute("name","qty"+[i]);
    }
}

// 로그인, 장바구니, 마이페이지를 클릭하면 해당 페이지로 이동 //
// + 로그인 안했을때 장바구니, 마이페이지를 클릭하면 alert //
const login = document.querySelector("#login");
const cart = document.querySelector("#cart");
const mypage= document.querySelector("#mypage");
if (login != null) {
    login.addEventListener("click", function() {
        location.href = "/login";
    });
}
cart.addEventListener("click", function() {
    if(login != null) {
        window.alert("로그인 후 이용해주세요.");
    } else {
        const id = document.querySelector("#userid").value;
        location.href = "/cart?id=" + id;
    }
});
mypage.addEventListener("click", function() {
    if(login != null) {
        window.alert("로그인 후 이용해주세요.");
    } else {
        const id = document.querySelector("#userid").value;
        location.href = "/mypage?id=" + id;    
    }
});

// 로그아웃 //
const logout = document.querySelector("#logout");
if (logout != null) {
    logout.addEventListener("click", function() {
        const form = document.forms.logout;
        form.submit();
    });
}


//구매완료 페이지에서 구매내역조회 버튼 클릭시 해당 페이지로 이동//
const buyObtn = document.querySelector("#buyObtn");
buyObtn.addEventListener("click", function() {
    location.href = "/main";
});

// 주소 입력 //
const adrtable = document.querySelector("#addadrtable")
const address = document.querySelector(".addadrinput")
const addressname = document.querySelector(".adressname")
const insbtn = document.querySelector(".adrinsbtn")
const modbtn = document.querySelector(".adrmodbtn")

insbtn.addEventListener("click",(e) => {
    if (address.value == "") {
        alert("주소를 입력해주세요");
        e.preventDefault();
        address.focus();
    } else {
    addressname.innerHTML = address.value;
    adrtable.style.display = "none";
    }
})
modbtn.addEventListener("click",() => {
    adrtable.style.display = "table";
})
const orderbutton = document.querySelector(".orderbuttons")
orderbutton.addEventListener("submit",(e) => {
    if (addressname.innerHTML == "배송지를 입력해주세요.") {
        alert("주소를 입력해주세요");
        e.preventDefault();
    }
})



// // 수량에따라 주문금액 표기 -> 총(주문금액)결제하기 표기

// function calculateButton() {
//     var productRows = document.querySelectorAll('.cartListDetail'); // 상품 행 요소 가져오기
//     var total = 0;

//     // 상품 행의 주문 금액 계산
//     productRows.forEach(function(row) {
//         var count = parseInt(row.querySelector('.count').innerText); // 수량
//         var priceText = row.querySelector('.price').innerText; // 가격 텍스트 
//         var price = parseInt(priceText.replace(/[^0-9]/g, '')); // 가격 

//         var orderPrice = count * price; 
//         total += orderPrice; 

//         row.querySelector('.price').innerText = orderPrice.toLocaleString() + '원'; 
//     });

//     // 총 주문금액을 표시하는 요소 가져와서 값 업데이트
//     var orderBtn = document.querySelector('.cartBigorderbtn');
//     orderBtn.innerText = `총 ${total.toLocaleString()}원 결제하기`;
// }

// // 수량이 변경될 때마다 calculateButton 함수 호출
// var counts = document.querySelectorAll('.count');
// counts.forEach(function (count) {
//     count.addEventListener('input', calculateButton);
// });

// // 초기화: 페이지 로딩 시 총 주문금액 계산
// calculateButton();

