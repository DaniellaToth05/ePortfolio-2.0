<script>
    let { investments = [] } = $props();
  
    const totalValue = $derived(() =>
      investments.reduce(
        (sum, inv) => sum + inv.quantity * inv.price,
        0
      )
    );
  
    const totalBookValue = $derived(() =>
      investments.reduce(
        (sum, inv) => sum + inv.bookValue,
        0
      )
    );
  
    const totalGain = $derived(() =>
      totalValue() - totalBookValue()
    );
  
    const holdingsCount = $derived(() => investments.length);
  </script>
  
  <section class="summary">
    <div class="stat">
      <div class="label">Total Value</div>
      <div class="value">
        ${totalValue().toFixed(2)}
      </div>
    </div>
  
    <div class="stat">
      <div class="label">Total Gain</div>
      <div
        class="value"
        class:positive={totalGain() >= 0}
        class:negative={totalGain() < 0}
      >
        ${totalGain().toFixed(2)}
      </div>
    </div>
  
    <div class="stat">
      <div class="label">Holdings</div>
      <div class="value">
        {holdingsCount()}
      </div>
    </div>
  </section>
  
  <style>
    .summary {
      display: flex;
      gap: 2rem;
      margin-bottom: 2rem;
    }
  
    .stat {
      padding: 1rem;
      border: 1px solid #ddd;
      border-radius: 6px;
      min-width: 150px;
    }
  
    .label {
      font-size: 0.85rem;
      color: #666;
    }
  
    .value {
      font-size: 1.25rem;
      font-weight: 600;
    }
  
    .positive {
      color: green;
    }
  
    .negative {
      color: red;
    }
  </style>
  