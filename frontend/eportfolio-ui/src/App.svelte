<script>
  import PortfolioTable from "./lib/PortfolioTable.svelte";
  import SellModal from "./lib/SellModal.svelte";

  // ---------- STATE ----------
  let portfolio = $state(null);
  let error = $state(null);

  // buy form fields
  let symbol = $state("");
  let name = $state("");
  let quantity = $state(0);
  let price = $state(0);
  let sellOpen = $state(false);
  let sellSymbol = $state("");


  // ---------- EFFECTS ----------
  $effect(() => {
    loadPortfolio();
  });

  // ---------- FUNCTIONS ----------
  async function loadPortfolio() {
    try {
      const res = await fetch("http://localhost:8080/api/portfolio");
      if (!res.ok) {
        throw new Error("Failed to load portfolio");
      }
      portfolio = await res.json();
    } catch (e) {
      error = e.message;
    }
  }

  async function buyInvestment() {
    try {
      const res = await fetch("http://localhost:8080/api/buy", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
          symbol,
          name,
          quantity: Number(quantity),
          price: Number(price)
        })
      });

      if (!res.ok) {
        throw new Error("Buy failed");
      }

      // reset form
      symbol = "";
      name = "";
      quantity = 0;
      price = 0;

      await loadPortfolio();
    } catch (e) {
      error = e.message;
    }
  }

  function openSell(symbol) {
    sellSymbol = symbol;
    sellOpen = true;
  }

  async function confirmSell(symbol, quantity, price) {
    try {
      const res = await fetch("http://localhost:8080/api/sell", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ symbol, quantity, price })
      });

      if (!res.ok) {
        throw new Error("Sell failed");
      }

      sellOpen = false;
      await loadPortfolio();
    } catch (e) {
      error = e.message;
    }
  }

</script>

<main>
  <h1>ePortfolio</h1>

  <h2>Buy Investment</h2>

  <div>
    <input placeholder="Symbol" bind:value={symbol} />
    <input placeholder="Name" bind:value={name} />
    <input type="number" placeholder="Quantity" bind:value={quantity} />
    <input type="number" placeholder="Price" bind:value={price} />
    <button onclick={buyInvestment}>Buy</button>
  </div>

  <h2>Portfolio</h2>

  {#if error}
    <p style="color: red;">{error}</p>
  {:else if !portfolio}
    <p>Loading...</p>
  {:else}
    <PortfolioTable
      portfolio={portfolio}
      onSell={openSell}
    />
  {/if}

  <SellModal
    open={sellOpen}
    symbol={sellSymbol}
    onConfirm={confirmSell}
    onClose={() => (sellOpen = false)}
  />


</main>
