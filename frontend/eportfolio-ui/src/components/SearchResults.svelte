<script>
    let {
      results = [],
      isFiltered = false,
      onClear
    } = $props();
  </script>
  
  <section class="search-results container">
    <div class="results-card">
      <div class="results-header">
        <h3>{isFiltered ? "Search Results" : "Portfolio"}</h3>
        {#if isFiltered}
            <button class="clear-btn" type="button" onclick={onClear}>
                Clear Search
            </button>
        {/if}
      </div>
  
      {#if results.length === 0}
        <div class="empty-state">
            {isFiltered
                ? "No investments matched your search."
                : "No investments yet — start tracking by adding your first one."}
        </div>
      {:else}
        <table class="results-table">
          <thead>
            <tr>
              <th>Symbol</th>
              <th>Name</th>
              <th>Quantity</th>
              <th>Price</th>
              <th>Book Value</th>
            </tr>
          </thead>
  
          <tbody>
            {#each results as inv}
              <tr>
                <td class="symbol">{inv.symbol}</td>
                <td>{inv.name}</td>
                <td>{inv.quantity}</td>
                <td>${inv.price}</td>
                <td>${inv.bookValue}</td>
              </tr>
            {/each}
          </tbody>
        </table>
      {/if}
    </div>
  </section>
  
