<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Expenses</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-50">
<%@ include file="_layout.jspf" %>

<div class="max-w-5xl mx-auto p-4">
    <h1 class="text-2xl font-semibold mb-4">Add Expense</h1>

    <form id="expForm" class="bg-white rounded-xl shadow p-5 grid grid-cols-1 md:grid-cols-4 gap-3">
        <select name="type" class="border rounded px-3 py-2" required>
            <option value="ADVERTISING">ADVERTISING</option>
            <option value="DELIVERY">DELIVERY</option>
            <option value="OTHER">OTHER</option>
        </select>
        <input name="date" type="date" class="border rounded px-3 py-2" required />
        <input name="amount" type="number" step="0.01" placeholder="Amount" class="border rounded px-3 py-2" required />
        <input name="note" placeholder="Note (optional)" class="border rounded px-3 py-2" />
        <button class="bg-black text-white rounded px-4 py-2">Save</button>
    </form>
</div>

<script>
    document.getElementById('expForm').addEventListener('submit', async (e)=>{
        e.preventDefault();
        const fd = new FormData(e.target);
        const payload = {
            type: fd.get('type'),
            date: fd.get('date'),
            amount: Number(fd.get('amount')),
            note: fd.get('note') || null
        };
        const res = await fetch('/api/expenses', {
            method: 'POST', headers: {'Content-Type':'application/json'},
            body: JSON.stringify(payload)
        });
        if(!res.ok){ return alert('Expense failed'); }
        alert('Expense added');
        e.target.reset();
    });
</script>
</body>
</html>
