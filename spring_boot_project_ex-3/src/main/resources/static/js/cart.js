/**
 * 
 */
 
var sell_price;
var amount;

function init () {
	sell_price = document.form.sell_price.value;
	amount = document.form.amount.value;
	document.form.sum.value = sell_price;
	change();
}

function add () {
	hm = document.form.amount;
	sum = document.form.sum;
	hm.value ++ ;

	sum.value = parseInt(hm.value) * sell_price;
}

function del () {
	hm = document.form.amount;
	sum = document.form.sum;
		if (hm.value > 1) {
			hm.value -- ;
			sum.value = parseInt(hm.value) * sell_price;
		}
}

function change () {
	hm = document.form.amount;
	sum = document.form.sum;

		if (hm.value < 0) {
			hm.value = 0;
		}
	sum.value = parseInt(hm.value) * sell_price;
}  


function bankDisplay(frm) {
 
    var bank = frm.prdSelect2.selectedIndex;
 
    switch( bank ){
       case 0:
         frm.bank.value = '은행 및 계좌번호가 표시됩니다.';
         break;
       case 1:
         frm.bank.value = '(국X은행) 0XX-XX-XXXX-XXX';
         break;
       case 2:
         frm.bank.value = '(기X은행) XXX-0XXXXX-0X-0XX';
         break;
       case 3:
         frm.bank.value = '(우X은행) 1XX-XX-0XXXXXXX';
         break;
       case 4:
         frm.bank.value = '(주X은행) 0XXXXXX-0X-0XXXXX';
         break;
    }
    
    return true;
}