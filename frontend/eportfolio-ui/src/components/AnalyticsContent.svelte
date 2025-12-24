<script>
    let { investments = [] } = $props();
  
    const totalValue = $derived(() =>
      investments.reduce((sum, inv) => sum + inv.quantity * inv.price, 0)
    );
  
    const totalBookValue = $derived(() =>
      investments.reduce((sum, inv) => sum + inv.bookValue, 0)
    );
  
    const totalGain = $derived(() => totalValue() - totalBookValue());
  
    const allocations = $derived(() =>
      investments.map(inv => {
        const value = inv.quantity * inv.price;
        return {
          ...inv,
          value,
          percent: totalValue() > 0 ? (value / totalValue()) * 100 : 0
        };
      })
    );

    const topPerformer = $derived(() => {
    if (investments.length === 0){
        return null;
    } 

    return investments.reduce((best, inv) => {
        const gain = inv.quantity * inv.price - inv.bookValue;
        const bestGain =
        best.quantity * best.price - best.bookValue;

        return gain > bestGain ? inv : best;
    });
    });

    const topGain = $derived(() =>
    topPerformer()
        ? topPerformer().quantity * topPerformer().price -
        topPerformer().bookValue
        : 0
    );

  </script>
  <section class="analytics">
    <h2 class="analytics-title">Performance Analytics</h2>
  
    <div class="summary">
      <div class="stat">
        <span>Total Value</span>
        <strong>${totalValue().toFixed(2)}</strong>
      </div>
  
      <div class="stat">
        <span>Total Gain</span>
        <strong
          class:positive={totalGain() >= 0}
          class:negative={totalGain() < 0}
        >
          ${totalGain().toFixed(2)}
        </strong>
      </div>
    </div>
  
    <div class="allocation">
      <h3>Portfolio Allocation</h3>
  
      {#if allocations().length === 0}
        <p class="empty">No investments yet.</p>
      {:else}
        {#each allocations() as inv}
          <div class="row">
            <div class="row-header">
              <span class="symbol">{inv.symbol}</span>
              <span class="percent">{inv.percent.toFixed(1)}%</span>
            </div>
  
            <div class="bar">
              <div class="fill" style="width: {inv.percent}%"></div>
            </div>
          </div>
        {/each}
      {/if}
    </div>

    <div class="insight-card">
        <h3>Top Performer</h3>
      
        {#if topPerformer()}
          <div class="insight-content">
            <span class="symbol">{topPerformer().symbol}</span>
            <span class="gain positive">
              +${topGain().toFixed(2)}
            </span>
          </div>
        {:else}
          <p class="empty">No investments yet.</p>
        {/if}
      </div>
      
  </section>
