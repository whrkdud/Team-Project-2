// $(document).ready(function($) {
//         $(".scroll_move").click(function(event){ console.log(".scroll_move"); 
//             event.preventDefault();
//         });
//         $('html, body').animate({scrollTop:$(this.hash).offset().bottom - 60}, 100);
//     });


document.addEventListener('DOMContentLoaded', () => {
    function modifybtn(e){
        // 회원정보 수정 공백 처리 //
        const form = document.querySelector(".form");
        const pw = document.querySelector(".pwInput");
        const names = document.querySelector(".nameInput");
        const email = document.querySelector(".emailInput");
        const phonenum = document.querySelector(".phonenumInput")
        if (pw.value == "") {
            alert("비밀번호를 입력해주세요");
            e.preventDefault();
            pw.focus();
        } else if (names.value == "") {
            alert("이름을 입력해주세요");
            e.preventDefault();
            names.focus();
        } else if (phonenum.value =="") {
            alert("전화번호를 입력해주세요");
            e.preventDefault();
            phonenum.focus();
        } else if (email.value == "") {
            alert("이메일 주소를 입력해주세요");
            e.preventDefault();
            email.focus();
        } else {
            alert('회원정보가 정상적으로 수정되었습니다.');
        }
    };
    const modifybutton = document.querySelector("#modifyButton")
    modifybutton.addEventListener("click", modifybtn)
})



function orderdeletebtn(){
    alert('리뷰가 정상적으로 삭제되었습니다.');
}

function searchdeletebtn(){
    alert('검색 내역이 정상적으로 삭제되었습니다.');
}

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
logout.addEventListener("click", function() {
    const form = document.forms.logout;
    form.submit();
});