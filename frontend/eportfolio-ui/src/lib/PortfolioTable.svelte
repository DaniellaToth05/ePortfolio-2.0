<script>
    // props
    let { portfolio, onSell } = $props();
  </script>
  
  {#if portfolio.investments.length === 0}
    <p>No investments yet.</p>
  {:else}
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
        {#each portfolio.investments as inv}
          <tr>
            <td>{inv.symbol}</td>
            <td>{inv.name}</td>
            <td>{inv.quantity}</td>
            <td>${inv.price}</td>
            <td>${inv.bookValue}</td>
            <td>
              <button on:click={() => onSell(inv.symbol)}>
                Sell
              </button>
            </td>
          </tr>
        {/each}
      </tbody>
    </table>
  {/if}
  