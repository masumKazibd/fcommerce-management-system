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

    <form id="saleForm" class="bg-white rounded-xl shadow p-5 grid grid-cols-1 md:grid-cols-4 gap-3 mb-8">
        <select name="productId" id="productId" class="border rounded px-3 py-2" required></select>
        <input name="quantity" type="number" min="1" placeholder="Qty" class="border rounded px-3 py-2" required />
        <input name="date" type="date" class="border rounded px-3 py-2" required />
        <input name="unitSellPrice" type="number" step="0.01" placeholder="(optional) Unit Sell Price" class="border rounded px-3 py-2" />
        <button class="bg-black text-white rounded px-4 py-2 md:col-span-1">Save</button>
    </form>

    <h1 class="text-2xl font-semibold mb-4 mt-8">Sales History</h1>
    <div id="salesHistory" class="bg-white rounded-xl shadow">
        <table class="w-full text-left">
            <thead>
            <tr class="border-b">
                <th class="p-3">Date</th>
                <th class="p-3">Product</th>
                <th class="p-3">Qty</th>
                <th class="p-3">Price</th>
                <th class="p-3">Total</th>
            </tr>
            </thead>
            <tbody id="salesRows">
            <tr><td colspan="5" class="p-3 text-center text-gray-500">Loading sales history...</td></tr>
            </tbody>
        </table>
    </div>
</div>

<script>
    const productSel = document.getElementById('productId');
    const salesRows = document.getElementById('salesRows');
    let productMap = {};

    function loadProducts() {
        return fetch('/api/products')
            .then(r => r.json())
            .then(list => {
                productSel.innerHTML = list.map(p => {
                    productMap[p.id] = p.name;
                    return `<option value="\${p.id}">\${p.name} (Stock: \${p.stockQty})</option>`;
                }).join('');
            });
    }

    function loadSales() {
        fetch('/api/sales')
            .then(r => r.json())
            .then(list => {
                if (list.length === 0) {
                    salesRows.innerHTML = '<tr><td colspan="5" class="p-3 text-center text-gray-500">No sales history found.</td></tr>';
                    return;
                }

                salesRows.innerHTML = list.map(s => {
                    const productName = productMap[s.productId] || 'Unknown Product';
                    const total = (s.quantity * s.unitSellPrice).toFixed(2);
                    const formattedDate = new Date(s.date).toLocaleDateString();

                    return `
                        <tr class="border-b hover:bg-gray-50">
                            <td class="p-3">\${formattedDate}</td>
                            <td class="p-3">\${productName}</td>
                            <td class="p-3">\${s.quantity}</td>
                            <td class="p-3">\${s.unitSellPrice}</td>
                            <td class="p-3">\${total}</td>
                        </tr>
                    `;
                }).join('');
            })
            .catch(e => {
                salesRows.innerHTML = '<tr><td colspan="5" class="p-3 text-center text-red-500">Failed to load sales history.</td></tr>';
                console.error("Failed to load sales history:", e);
            });
    }

    loadProducts().then(loadSales);

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
        alert('Successfully sold');
        e.target.reset();
        loadSales();
    });
</script>
</body>
</html>