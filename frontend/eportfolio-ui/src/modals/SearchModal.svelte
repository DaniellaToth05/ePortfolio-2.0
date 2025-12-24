<script>
    let {
      open = false,
      onClose,
      onSubmit
    } = $props();
  
    let symbol = $state("");
    let keywords = $state("");
    let priceRange = $state("");
  
    function submit(e) {
      e.preventDefault();
  
      onSubmit({
        symbol: symbol.trim(),
        keywords: keywords.trim(),
        priceRange: priceRange.trim()
      });
    }
  </script>
  
  {#if open}
    <div class="modal-overlay">
      <div class="modal">
        <button class="close-btn" type="button" onclick={onClose}>×</button>
  
        <div class="modal-header">
          <h2 class="modal-title">Portfolio Search</h2>
          <p class="modal-subtitle">Filter your investments</p>
        </div>
  
        <form onsubmit={submit}>
          <div class="form-group">
            <label class="form-label" for="search-symbol">Symbol</label>
            <input
              id="search-symbol"
              class="form-input"
              placeholder="e.g. AAPL"
              bind:value={symbol}
            />
          </div>
  
          <div class="form-group">
            <label class="form-label" for="search-keywords">Keywords</label>
            <input
              id="search-keywords"
              class="form-input"
              placeholder="e.g. apple tech"
              bind:value={keywords}
            />
          </div>
  
          <div class="form-group">
            <label class="form-label" for="search-price">Price Range</label>
            <input
              id="search-price"
              class="form-input"
              placeholder="e.g. 50-200"
              bind:value={priceRange}
            />
          </div>
  
          <div class="button-group">
            <button
              type="button"
              class="btn btn-secondary"
              onclick={onClose}
            >
              Cancel
            </button>
  
            <button type="submit" class="btn btn-primary">
              Search
            </button>
          </div>
        </form>
      </div>
    </div>
  {/if}
  