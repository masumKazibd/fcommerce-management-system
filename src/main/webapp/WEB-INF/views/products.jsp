<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Products</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-50">
<%@ include file="_layout.jspf" %>

<div class="max-w-5xl mx-auto p-4">
    <h1 class="text-2xl font-semibold mb-4">Products</h1>

    <div class="bg-white rounded-xl shadow p-5 mb-6">
        <h2 class="font-semibold mb-3">Add Product</h2>
        <form id="createForm" class="grid grid-cols-1 md:grid-cols-5 gap-3">
            <input name="name" placeholder="Name" class="border rounded px-3 py-2" required />
            <input name="buyPrice" type="number" step="0.01" placeholder="Buy Price" class="border rounded px-3 py-2" required />
            <input name="sellPrice" type="number" step="0.01" placeholder="Sell Price" class="border rounded px-3 py-2" required />
            <input name="stockQty" type="number" placeholder="Stock Qty" class="border rounded px-3 py-2" required />
            <button class="bg-black text-white rounded px-4 py-2">Save</button>
        </form>
    </div>

    <div class="bg-white rounded-xl shadow">
        <table class="w-full text-left">
            <thead>
            <tr class="border-b">
                <th class="p-3">ID</th>
                <th class="p-3">Name</th>
                <th class="p-3">Buy</th>
                <th class="p-3">Sell</th>
                <th class="p-3">Stock</th>
            </tr>
            </thead>
            <tbody id="rows"></tbody>
        </table>
    </div>
</div>

<script>
    const rows = document.getElementById('rows');

    function load() {
        fetch('/api/products')
            .then(r => r.json())
            .then(list => {
                rows.innerHTML = list.map(p => `
            <tr class="border-b hover:bg-gray-50">
              <td class="p-3">\${p.id}</td>   <td class="p-3">\${p.name}</td> <td class="p-3">\${p.buyPrice}</td> <td class="p-3">\${p.sellPrice}</td> <td class="p-3">\${p.stockQty}</td> </tr>
          `).join('');
            });
    }
    load();

    document.getElementById('createForm').addEventListener('submit', async (e) => {
        e.preventDefault();
        const fd = new FormData(e.target);
        const payload = {
            name: fd.get('name'),
            buyPrice: Number(fd.get('buyPrice')),
            sellPrice: Number(fd.get('sellPrice')),
            stockQty: Number(fd.get('stockQty'))
        };
        const res = await fetch('/api/products', {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify(payload)
        });
        if (!res.ok) return alert('Create failed');
        e.target.reset();
        load();
    });
</script>
</body>
</html>