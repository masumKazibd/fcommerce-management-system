<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Sales</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-50">
<%@ include file="_layout.jspf" %>

<div class="max-w-5xl mx-auto p-4">
    <h1 class="text-2xl font-semibold mb-4">Create Sale</h1>

    <form id="saleForm" class="bg-white rounded-xl shadow p-5 grid grid-cols-1 md:grid-cols-4 gap-3">
        <select name="productId" id="productId" class="border rounded px-3 py-2" required></select>
        <input name="quantity" type="number" min="1" placeholder="Qty" class="border rounded px-3 py-2" required />
        <input name="date" type="date" class="border rounded px-3 py-2" required />
        <input name="unitSellPrice" type="number" step="0.01" placeholder="(optional) Unit Sell Price" class="border rounded px-3 py-2" />
        <button class="bg-black text-white rounded px-4 py-2">Save</button>
    </form>
</div>

<script>
    const productSel = document.getElementById('productId');
    fetch('/api/products').then(r=>r.json()).then(list=>{
        productSel.innerHTML = list.map(p=>`<option value="${p.id}">${p.name} (stock ${p.stockQty})</option>`).join('');
    });

    document.getElementById('saleForm').addEventListener('submit', async (e)=>{
        e.preventDefault();
        const fd = new FormData(e.target);
        const payload = {
            productId: Number(fd.get('productId')),
            quantity: Number(fd.get('quantity')),
            date: fd.get('date'),
            unitSellPrice: fd.get('unitSellPrice') ? Number(fd.get('unitSellPrice')) : null
        };
        const res = await fetch('/api/sales', {
            method: 'POST', headers: {'Content-Type':'application/json'},
            body: JSON.stringify(payload)
        });
        if(!res.ok){
            const t = await res.text();
            return alert('Sale failed: ' + t);
        }
        alert('Sale created');
        e.target.reset();
    });
</script>
</body>
</html>
