<script>
  import SellModal from "./SellModal.svelte";
  import UpdatePriceModal from "./UpdatePriceModal.svelte";

  let {
    investments = [],
    loadPortfolio
  } = $props();

  // sell modal state
  let sellOpen = $state(false);
  let sellSymbol = $state("");

  // update modal state
  let updateOpen = $state(false);
  let updateSymbol = $state("");
  let updateCurrentPrice = $state(0);

  function openSell(inv) {
    sellSymbol = inv.symbol;
    sellOpen = true;
  }

  function openUpdate(inv) {
    updateSymbol = inv.symbol;
    updateCurrentPrice = inv.price;
    updateOpen = true;
  }
</script>

<table border="1" cellpadding="8" cellspacing="0">
  <thead>
    <tr>
      <th>Symbol</th>
      <th>Name</th>
      <th>Quantity</th>
      <th>Price</th>
      <th>Book Value</th>
      <th>Actions</th>
    </tr>
  </thead>

  <tbody>
    {#each investments as inv}
      <tr>
        <td>{inv.symbol}</td>
        <td>{inv.name}</td>
        <td>{inv.quantity}</td>
        <td>${inv.price}</td>
        <td>${inv.bookValue}</td>
        <td>
          <button type="button" onclick={() => openSell(inv)}>
            Sell
          </button>
          <button type="button" onclick={() => openUpdate(inv)}>
            Update
          </button>
        </td>
      </tr>
    {/each}
  </tbody>
</table>

<SellModal
  open={sellOpen}
  symbol={sellSymbol}
  onClose={() => sellOpen = false}
  onConfirm={async (symbol, quantity, price) => {
    await fetch("http://localhost:8080/api/sell", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ symbol, quantity, price })
    });

    sellOpen = false;
    loadPortfolio();
  }}
/>

<UpdatePriceModal
  open={updateOpen}
  symbol={updateSymbol}
  currentPrice={updateCurrentPrice}
  onClose={() => updateOpen = false}
  onUpdated={loadPortfolio}
/>
